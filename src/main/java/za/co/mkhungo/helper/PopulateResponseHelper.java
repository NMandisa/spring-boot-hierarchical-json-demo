package za.co.mkhungo.helper;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Component
public class PopulateResponseHelper {

    public CustomerResponse populateCustomerResponse(List<CustomerDTO> customers) {
        if(Objects.isNull(customers)) {
            return CustomerResponse.builder().build();
        }
        return CustomerResponse.builder()
                .customers(CustomerTree.builder()
                        .customer(populateCustomerSubTrees(customers)).build()).build();
    }
    public CustomerResponse populateCustomerResponse(CustomerDTO customer) throws CustomerNotFoundException {
        if(Objects.isNull(customer)) {
            return CustomerResponse.builder().build();
        }
        return CustomerResponse.builder()
                .customers(CustomerTree.builder()
                        .customer(List.of(populateCustomerSubTree(customer))).build()).build();
    }
    public OrderResponse populateOrderResponse(List<OrderDTO> orders) {
        if(Objects.isNull(orders)) {
            return OrderResponse.builder().build();
        }
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTrees(orders)).build()).build();
    }
    public OrderResponse populateOrderResponse(OrderDTO order) throws OrderNotFoundException {
        if(Objects.isNull(order)) {
            return OrderResponse.builder().build();
        }
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTree(order)).build()).build();
    }
    public ProductResponse populateProductResponse(List<ProductDTO> products) {
        if(Objects.isNull(products)) {
            return ProductResponse.builder().build();
        }
        return ProductResponse.builder().products(populateProductTree(products)).build();
    }
    public ProductResponse populateProductResponse(ProductDTO product) throws ProductNotFoundException {
        if(Objects.isNull(product)) {
            return ProductResponse.builder().build();
        }
        return ProductResponse.builder().products(ProductTree.builder()
                .product(populateProductSubTrees(product)).build()).build();
    }
    private List<OrderSubTree> populateOrderSubTrees(List<OrderDTO> orders) {
        if(Objects.isNull(orders)) {
            return List.of();
        }
        return orders.stream().map(this::mapOrderToSubTree).toList();
    }
    private OrderSubTree mapOrderToSubTree(OrderDTO orderDTO) {
        if(Objects.isNull(orderDTO)) {
            return OrderSubTree.builder().build();
        }
        try {
            return OrderSubTree.builder()
                    .id(orderDTO.getId())
                    .orderStatus(orderDTO.getOrderStatus())
                    .placedOn(orderDTO.getPlacedOn())
                    .products(populateProductTree(orderDTO.getProducts()))
                    .build()
                    .add(
                            linkTo(methodOn(OrderController.class).getOrderById(orderDTO.getId())).withSelfRel(),
                            linkTo(methodOn(OrderController.class).getOrders()).withRel("orders")
                    );
        } catch (OrderNotFoundException e) {
            throw new OrderException(e.getMessage());
        }
    }
    private List<OrderSubTree> populateOrderSubTree(OrderDTO order) throws OrderNotFoundException {
        return List.of(OrderSubTree.builder().products(populateProductTree(order.getProducts()))
                .id(order.getId()).placedOn(order.getPlacedOn()).orderStatus(order.getOrderStatus()).build()
                .add(linkTo(methodOn(OrderController.class).getOrderById(order.getId())).withSelfRel(),
                        linkTo(methodOn(OrderController.class).getOrders()).withRel("orders")));
    }
    private ProductTree populateProductTree(List<ProductDTO> products) {
        if(Objects.isNull(products)) {
            return ProductTree.builder().build();
        }
        return ProductTree.builder().product(products.stream().map(productDTO ->
                {
                    try {
                        return ProductSubTree.builder().id(productDTO.getId()).name(productDTO.getName()).price(productDTO.getPrice())
                                .ratings(populateRatingTree(productDTO.getRatings())).build()
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
                 .ratings(populateRatingTree(productDTO.getRatings())).build()
                 .add(linkTo(methodOn(ProductController.class).getProductById(productDTO.getId())).withSelfRel(),
                         linkTo(methodOn(ProductController.class).getProducts()).withRel("products"))
         ));
    }
    private RatingTree populateRatingTree(@NonNull List<RatingDTO> ratings) {
        return RatingTree.builder().rating(ratings.stream().map(ratingDTO ->
                        RatingSubTree.builder().id(ratingDTO.getId()).rating(ratingDTO.getRate())
                                .reviews(populateReviewTree(ratingDTO.getReviews())).build()).toList()).build();
    }
    private ReviewTree populateReviewTree(@NonNull List<ReviewDTO> reviews){
        return ReviewTree.builder().review(reviews.stream().map(reviewDTO -> ReviewSubTree.builder()
                        .id(reviewDTO.getId()).tagLine(reviewDTO.getTagLine())
                        .comment(reviewDTO.getComment()).build()).toList()).build();
    }
    private List<CustomerSubTree> populateCustomerSubTrees(@NonNull List<CustomerDTO> customers){
        return customers.stream().map(this::mapCustomerToSubTree).toList();
    }
    private CustomerSubTree populateCustomerSubTree(@NonNull CustomerDTO customerDTO) throws CustomerNotFoundException {
            return CustomerSubTree.builder().id(customerDTO.getId())
                    .firstName(customerDTO.getFirstName()).surname(customerDTO.getSurname()).orders(OrderTree.builder()
                            .order(populateOrderSubTrees(customerDTO.getOrders())).build()).build()
                    .add(linkTo(methodOn(CustomerController.class).getCustomer(customerDTO.getId())).withSelfRel(),
                            linkTo(methodOn(CustomerController.class).getCustomers()).withRel("customers"));
    }

    private CustomerSubTree mapCustomerToSubTree(CustomerDTO customerDTO) {
        try {
            return CustomerSubTree.builder()
                    .id(customerDTO.getId())
                    .firstName(customerDTO.getFirstName())
                    .surname(customerDTO.getSurname())
                    .orders(OrderTree.builder()
                            .order(populateOrderSubTrees(customerDTO.getOrders()))
                            .build())
                    .build()
                    .add(linkTo(methodOn(CustomerController.class).getCustomer(customerDTO.getId())).withSelfRel(),
                            linkTo(methodOn(CustomerController.class).getCustomers()).withRel("customers"));
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found: {}", e.getMessage());
            throw new CustomerException(e.getMessage());
        }
    }
}