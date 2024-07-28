package za.co.mkhungo.helper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import za.co.mkhungo.controller.CustomerController;
import za.co.mkhungo.controller.OrderController;
import za.co.mkhungo.controller.ProductController;
import za.co.mkhungo.dto.*;
import za.co.mkhungo.exception.*;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.response.OrderResponse;
import za.co.mkhungo.response.ProductResponse;
import za.co.mkhungo.response.node.*;
import za.co.mkhungo.response.node.sub.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class PopulateResponseHelper {

    public CustomerResponse populateCustomerTree(@NonNull List<CustomerDTO> customers) {
        return CustomerResponse.builder()
                .customers(CustomerTree.builder()
                        .customer(populateCustomerSubTrees(customers)).build()).build();
    }
    public CustomerResponse populateCustomerTree(@NonNull CustomerDTO customer) throws CustomerNotFoundException {
        return CustomerResponse.builder()
                .customers(CustomerTree.builder()
                        .customer(List.of(populateCustomerSubTree(customer))).build()).build();
    }
    public OrderResponse populateOrderTree(@NonNull List<OrderDTO> orders) {
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTrees(orders)).build()).build();
    }
    public OrderResponse populateOrderTree(@NonNull OrderDTO order) throws OrderNotFoundException {
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTree(order)).build()).build();
    }
    public ProductResponse populateProductTree(@NonNull List<ProductDTO> products) {
        return ProductResponse.builder().products(ProductTree.builder().product(products.stream()
                .map(productDTO -> {
                    try {
                        return ProductSubTree.builder().id(productDTO.getId())
                                .name(productDTO.getName()).price(productDTO.getPrice()).ratings(
                                        RatingTree.builder().rating(populateRatingSubTree(productDTO)).build())
                                .build().add(linkTo(methodOn(ProductController.class).getProductById(productDTO.getId())).withSelfRel(),
                                        linkTo(methodOn(ProductController.class).getProducts()).withRel("products"));
                    } catch (ProductNotFoundException e) {
                        throw new ProductException(e.getMessage());
                    }
                }).toList()).build()).build();
    }
    public ProductResponse populateProductTree(@NonNull ProductDTO product) throws ProductNotFoundException {
        return ProductResponse.builder().products(ProductTree.builder()
                .product(populateProductSubTrees(product)).build()).build();
    }
    private List<OrderSubTree> populateOrderSubTrees(@NonNull List<OrderDTO> orders) {
        return orders.stream().map(orderDTO -> {
                            try {
                                return OrderSubTree.builder().id(orderDTO.getId())
                                        .orderStatus(orderDTO.getOrderStatus()).placedOn(orderDTO.getPlacedOn())
                                        .products(populateProductTree(orderDTO)).build()
                                        .add(linkTo(methodOn(OrderController.class).getOrderById(orderDTO.getId())).withSelfRel(),
                                                linkTo(methodOn(OrderController.class).getOrders()).withRel("orders"));
                            } catch (OrderNotFoundException e) {
                                throw new OrderException(e.getMessage());
                            }
                        }).toList();
    }
    private List<OrderSubTree> populateOrderSubTree(@NonNull OrderDTO order) throws OrderNotFoundException {
        return List.of(OrderSubTree.builder().products(populateProductTree(order))
                .id(order.getId()).placedOn(order.getPlacedOn()).orderStatus(order.getOrderStatus()).build()
                .add(linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel(),
                        linkTo(methodOn(OrderController.class).getOrders()).withRel("orders")));
    }
    private ProductTree populateProductTree(@NonNull OrderDTO orderDTO) {
        return ProductTree.builder().product(orderDTO.getProducts().stream().map(productDTO ->
                {
                    try {
                        return ProductSubTree.builder().id(productDTO.getId()).name(productDTO.getName()).price(productDTO.getPrice())
                                .ratings(RatingTree.builder().rating(populateRatingSubTree(productDTO)).build()).build()
                                .add(linkTo(methodOn(ProductController.class).getProductById(productDTO.getId())).withSelfRel(),
                                        linkTo(methodOn(ProductController.class).getProducts()).withRel("products"));
                    } catch (ProductNotFoundException e) {
                        throw new ProductException(e.getMessage());
                    }
                }
        ).toList()).build();
    }
    private List<ProductSubTree> populateProductSubTrees(@NonNull ProductDTO productDTO) throws ProductNotFoundException {
         return List.of((ProductSubTree.builder().id(productDTO.getId())
                .name(productDTO.getName()).price(productDTO.getPrice())
                 .ratings(populateRatingTree(productDTO)).build()
                 .add(linkTo(methodOn(ProductController.class).getProductById(productDTO.getId())).withSelfRel(),
                         linkTo(methodOn(ProductController.class).getProducts()).withRel("products"))
         ));
    }
    private List<RatingSubTree> populateRatingSubTree(@NonNull ProductDTO productDTO) {
        return productDTO.getRatings().stream().map(ratingDTO ->
                        RatingSubTree.builder().id(ratingDTO.getId()).rating(ratingDTO.getRate())
                                .reviews(ReviewTree.builder().review(populateReviewSubTrees(ratingDTO)).build()).build()).toList();
    }
    private RatingTree populateRatingTree(@NonNull ProductDTO productDTO) {
        return RatingTree.builder().rating(populateRatingSubTree(productDTO)).build();
    }
    private List<ReviewSubTree> populateReviewSubTrees(@NonNull RatingDTO ratingDTO){
        return ratingDTO.getReviews().stream().map(reviewDTO -> ReviewSubTree.builder()
                        .id(reviewDTO.getId()).tagLine(reviewDTO.getTagLine())
                        .comment(reviewDTO.getComment()).build()).toList();
    }
    private List<CustomerSubTree> populateCustomerSubTrees(@NonNull List<CustomerDTO> customers){
        return customers.stream().map(customerDTO ->
                {
                    try {
                        return CustomerSubTree.builder()
                                .id(customerDTO.getId()).firstName(customerDTO.getFirstName())
                                .surname(customerDTO.getSurname())
                                .orders(OrderTree.builder()
                                        .order(populateOrderSubTrees(customerDTO.getOrders())).build())
                                .build().add(linkTo(methodOn(CustomerController.class).getCustomer(customerDTO.getId())).withSelfRel(),
                                        linkTo(methodOn(CustomerController.class).getCustomers()).withRel("customers"));
                    } catch (CustomerNotFoundException e) {
                        throw new CustomerException(e.getMessage());
                    }
                }).toList();
    }
    private CustomerSubTree populateCustomerSubTree(@NonNull CustomerDTO customerDTO) throws CustomerNotFoundException {
            return CustomerSubTree.builder().id(customerDTO.getId())
                    .firstName(customerDTO.getFirstName()).surname(customerDTO.getSurname()).orders(OrderTree.builder()
                            .order(populateOrderSubTrees(customerDTO.getOrders())).build()).build()
                    .add(linkTo(methodOn(CustomerController.class).getCustomer(customerDTO.getId())).withSelfRel(),
                            linkTo(methodOn(CustomerController.class).getCustomers()).withRel("customers"));
    }
}