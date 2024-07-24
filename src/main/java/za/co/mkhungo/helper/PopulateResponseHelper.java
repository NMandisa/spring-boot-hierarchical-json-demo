package za.co.mkhungo.helper;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.*;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.response.OrderResponse;
import za.co.mkhungo.response.ProductResponse;
import za.co.mkhungo.response.node.*;
import za.co.mkhungo.response.node.sub.*;

import java.util.ArrayList;
import java.util.List;

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
    public CustomerResponse populateCustomerTree(@NonNull CustomerDTO customer) {
        return CustomerResponse.builder()
                .customers(CustomerTree.builder()
                        .customer(List.of(populateCustomerSubTree(customer))).build()).build();
    }
    public OrderResponse populateOrderTree(@NonNull List<OrderDTO> orders) {
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTrees(orders)).build()).build();
    }
    public OrderResponse populateOrderTree(@NonNull OrderDTO order) {
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTree(order)).build()).build();
    }
    public ProductResponse populateProductTree(@NonNull List<ProductDTO> products) {
        List<ProductSubTree> productSubTrees = new ArrayList<>();
        products.forEach(productDTO -> productSubTrees.add(ProductSubTree.builder()
                .id(productDTO.getId())
                .name(productDTO.getName()).price(productDTO.getPrice())
                .ratings(RatingTree.builder().rating(populateRatingSubTree(productDTO)).build()).build()));
        return ProductResponse.builder()
                .products(ProductTree.builder()
                .product(productSubTrees).build()).build();

    }
    private List<OrderSubTree> populateOrderSubTrees(@NonNull List<OrderDTO> orders) {
        List<OrderSubTree> orderSubTrees = new ArrayList<>();
        orders.forEach(orderDTO -> orderSubTrees.add(OrderSubTree.builder()
                .id(orderDTO.getId())
                .orderStatus(orderDTO.getOrderStatus())
                .placedOn(orderDTO.getPlacedOn())
                .products(populateProductTree(orderDTO)).build()));
        return orderSubTrees;
    }
    private List<OrderSubTree> populateOrderSubTree(@NonNull OrderDTO order) {
        return List.of(OrderSubTree.builder().products(populateProductTree(order))
                .id(order.getId())
                .placedOn(order.getPlacedOn())
                .orderStatus(order.getOrderStatus()).build());
    }
    private ProductTree populateProductTree(@NonNull OrderDTO orderDTO) {
        List<ProductDTO> productDTOS = orderDTO.getProducts();
        List<ProductSubTree> productSubTrees = new ArrayList<>();
        productDTOS.forEach(productDTO -> productSubTrees.add(ProductSubTree.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice()).build()));
        return ProductTree.builder().product(productSubTrees).build();
    }
    private List<RatingSubTree> populateRatingSubTree(@NonNull ProductDTO productDTO) {
        List<RatingSubTree> ratingSubTrees = new ArrayList<>();
        List<RatingDTO> ratingDTOs = productDTO.getRatings();
        ratingDTOs.forEach(ratingDTO -> ratingSubTrees.add(RatingSubTree.builder().id(ratingDTO.getId())
                .rating(ratingDTO.getRate())
                .reviews(ReviewTree.builder().review(populateReviewSubTrees(ratingDTO)).build())
                .build()));
        return ratingSubTrees;
    }
    private List<ReviewSubTree> populateReviewSubTrees(@NonNull RatingDTO ratingDTO){
        List<ReviewSubTree> reviewSubTrees = new ArrayList<>();
        List<ReviewDTO> reviewDTOs = ratingDTO.getReviews();
        reviewDTOs.forEach(reviewDTO -> reviewSubTrees.add(ReviewSubTree.builder().id(reviewDTO.getId())
                .tagLine(reviewDTO.getTagLine())
                .comment(reviewDTO.getComment()).build()));
        return reviewSubTrees;
    }
    private List<CustomerSubTree> populateCustomerSubTrees(@NonNull List<CustomerDTO> customers){
        List<CustomerSubTree> customerSubTrees = new ArrayList<>();
        customers.forEach(customerDTO -> customerSubTrees.add(CustomerSubTree.builder()
                .id(customerDTO.getId())
                .firstName(customerDTO.getFirstName())
                .surname(customerDTO.getSurname())
                .orders(OrderTree.builder()
                        .order(populateOrderSubTrees(customerDTO.getOrders())).build())
                .build()));
        return customerSubTrees;
    }
    private CustomerSubTree populateCustomerSubTree(@NonNull CustomerDTO customerDTO){
            return CustomerSubTree.builder().id(customerDTO.getId())
                    .firstName(customerDTO.getFirstName())
                    .surname(customerDTO.getSurname()).orders(OrderTree.builder()
                            .order(populateOrderSubTrees(customerDTO.getOrders())).build()).build();
    }
}
