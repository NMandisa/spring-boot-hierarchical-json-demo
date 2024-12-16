package za.co.mkhungo.helper;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.*;
import za.co.mkhungo.response.*;
import za.co.mkhungo.response.node.*;
import za.co.mkhungo.response.node.sub.*;
import za.co.mkhungo.strategy.impl.*;

import java.util.List;
import java.util.Objects;


/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Component
public class PopulateResponseHelper {

    private final ProductControllerLinkProvider productControllerLinkProvider;
    private final CustomerControllerLinkProvider customerControllerLinkProvider;
    private final OrderControllerLinkProvider orderControllerLinkProvider;
    private final RatingControllerLinkProvider ratingControllerLinkProvider;
    private final ReviewControllerLinkProvider reviewControllerLinkProvider;

    @Autowired
    public PopulateResponseHelper(ProductControllerLinkProvider productControllerLinkProvider, CustomerControllerLinkProvider customerControllerLinkProvider,
                                  OrderControllerLinkProvider orderControllerLinkProvider, RatingControllerLinkProvider ratingControllerLinkProvider, ReviewControllerLinkProvider reviewControllerLinkProvider) {
        this.productControllerLinkProvider = productControllerLinkProvider;
        this.customerControllerLinkProvider = customerControllerLinkProvider;
        this.orderControllerLinkProvider = orderControllerLinkProvider;
        this.ratingControllerLinkProvider = ratingControllerLinkProvider;
        this.reviewControllerLinkProvider = reviewControllerLinkProvider;
    }

    public CustomerResponse populateCustomerResponse(List<CustomerDTO> customers) {
        if (Objects.isNull(customers)) {
            return CustomerResponse.builder().build();
        }
        return CustomerResponse.builder()
                .customers(CustomerTree.builder()
                        .customer(populateCustomerSubTrees(customers)).build()).build();
    }

    public CustomerResponse populateCustomerResponse(CustomerDTO customer) {
        if (Objects.isNull(customer)) {
            return CustomerResponse.builder().build();
        }
        return CustomerResponse.builder()
                .customers(CustomerTree.builder()
                        .customer(List.of(populateCustomerSubTree(customer))).build()).build();
    }

    public OrderResponse populateOrderResponse(List<OrderDTO> orders) {
        if (Objects.isNull(orders)) {
            return OrderResponse.builder().build();
        }
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTrees(orders)).build()).build();
    }

    public OrderResponse populateOrderResponse(OrderDTO order) {
        if (Objects.isNull(order)) {
            return OrderResponse.builder().build();
        }
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTree(order)).build()).build();
    }

    public ProductResponse populateProductResponse(List<ProductDTO> products) {
        if (Objects.isNull(products)) {
            return ProductResponse.builder().build();
        }
        return ProductResponse.builder().products(populateProductTree(products)).build();
    }

    public ProductResponse populateProductResponse(ProductDTO product) {
        if (Objects.isNull(product)) {
            return ProductResponse.builder().build();
        }
        return ProductResponse.builder().products(ProductTree.builder()
                .product(populateProductSubTrees(product)).build()).build();
    }

    public ReviewResponse populateReviewResponse(ReviewDTO review) {
        if (Objects.isNull(review)) {
            return ReviewResponse.builder().build();
        }
        return ReviewResponse.builder().reviews(ReviewTree.builder()
                .review(populateReviewSubTrees(review)).build()).build();
    }

    public RatingResponse populateRatingResponse(RatingDTO rating) {
        if (Objects.isNull(rating)) {
            return RatingResponse.builder().build();
        }
        return RatingResponse.builder().ratings(RatingTree.builder()
                .rating(populateRatingSubTrees(rating)).build()).build();
    }

    private List<OrderSubTree> populateOrderSubTrees(List<OrderDTO> orders) {
        if (Objects.isNull(orders)) {
            return List.of();
        }
        return orders.stream().map(this::mapOrderToSubTree).toList();
    }

    private OrderSubTree mapOrderToSubTree(OrderDTO orderDTO) {
        if (Objects.isNull(orderDTO)) {
            return OrderSubTree.builder().build();
        }
        return OrderSubTree.builder().id(orderDTO.getId())
                .orderStatus(orderDTO.getOrderStatus()).placedOn(orderDTO.getPlacedOn())
                .products(populateProductTree(orderDTO.getProducts())).build()
                .add(orderControllerLinkProvider.getSelfLink(orderDTO.getId()));
    }

    private List<OrderSubTree> populateOrderSubTree(OrderDTO order) {
        return List.of(OrderSubTree.builder().products(populateProductTree(order.getProducts()))
                .id(order.getId()).placedOn(order.getPlacedOn()).orderStatus(order.getOrderStatus()).build()
                .add(orderControllerLinkProvider.getSelfLink(order.getId())));
    }

    private ProductTree populateProductTree(List<ProductDTO> products) {
        if (Objects.isNull(products)) {
            return ProductTree.builder().build();
        }
        return ProductTree.builder().product(products.stream().map(productDTO ->
                ProductSubTree.builder().id(productDTO.getId()).name(productDTO.getName()).price(productDTO.getPrice())
                        .ratings(populateRatingTree(productDTO.getRatings())).build()
                        .add(productControllerLinkProvider.getSelfLink(productDTO.getId()))).toList()).build();
    }

    private List<ProductSubTree> populateProductSubTrees(@NonNull ProductDTO productDTO) {
        return List.of((ProductSubTree.builder().id(productDTO.getId())
                .name(productDTO.getName()).price(productDTO.getPrice())
                .ratings(populateRatingTree(productDTO.getRatings())).build()
                .add(productControllerLinkProvider.getSelfLink(productDTO.getId()))));
    }

    private List<RatingSubTree> populateRatingSubTrees(@NonNull RatingDTO ratingDTO) {
        return List.of((RatingSubTree.builder().id(ratingDTO.getId())
                .rating(ratingDTO.getRate()).build()
                .add(ratingControllerLinkProvider.getSelfLink(ratingDTO.getId()))));
    }

    private List<ReviewSubTree> populateReviewSubTrees(@NonNull ReviewDTO reviewDTO) {
        return List.of((ReviewSubTree.builder().id(reviewDTO.getId())
                .tagLine(reviewDTO.getTagLine()).comment(reviewDTO.getComment()).build()
                .add(reviewControllerLinkProvider.getSelfLink(reviewDTO.getId()))));
    }

    private RatingTree populateRatingTree(@NonNull List<RatingDTO> ratings) {
        return RatingTree.builder().rating(ratings.stream().map(ratingDTO ->
                RatingSubTree.builder().id(ratingDTO.getId()).rating(ratingDTO.getRate())
                        .reviews(populateReviewTree(ratingDTO.getReviews())).build()
                        .add(ratingControllerLinkProvider.getSelfLink(ratingDTO.getId()))).toList()).build();
    }


    private ReviewTree populateReviewTree(@NonNull List<ReviewDTO> reviews) {
        return ReviewTree.builder().review(reviews.stream().map(reviewDTO -> ReviewSubTree.builder()
                .id(reviewDTO.getId()).tagLine(reviewDTO.getTagLine())
                .comment(reviewDTO.getComment()).build()
                        .add(reviewControllerLinkProvider.getSelfLink(reviewDTO.getId()))).toList()).build();
    }

    private List<CustomerSubTree> populateCustomerSubTrees(@NonNull List<CustomerDTO> customers) {
        return customers.stream().map(this::mapCustomerToSubTree).toList();
    }

    private CustomerSubTree populateCustomerSubTree(@NonNull CustomerDTO customerDTO) {
        return CustomerSubTree.builder().id(customerDTO.getId())
                .firstName(customerDTO.getFirstName()).surname(customerDTO.getSurname()).orders(OrderTree.builder()
                        .order(populateOrderSubTrees(customerDTO.getOrders())).build()).build()
                .add(customerControllerLinkProvider.getSelfLink(customerDTO.getId()));
    }

    private CustomerSubTree mapCustomerToSubTree(CustomerDTO customerDTO) {
        return CustomerSubTree.builder().id(customerDTO.getId())
                .firstName(customerDTO.getFirstName()).surname(customerDTO.getSurname())
                .orders(OrderTree.builder().order(populateOrderSubTrees(customerDTO.getOrders())).build())
                .build().add(customerControllerLinkProvider.getSelfLink(customerDTO.getId()));
    }
}
