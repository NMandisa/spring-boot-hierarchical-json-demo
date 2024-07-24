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
import java.util.Objects;

/**
 * @author Noxolo.Mkhungo
 */
@Component
public class PopulateResponseHelper {

    public CustomerResponse populateCustomerTree(List<CustomerDTO> customers) {
        CustomerResponse customerResponse = new CustomerResponse();
        CustomerTree customerTree = new CustomerTree();
        customerTree.setCustomer(populateCustomerSubTrees(customers));
        customerResponse.setCustomers(customerTree);
        return customerResponse;
    }
    public CustomerResponse populateCustomerTree(CustomerDTO customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        CustomerTree customerTree = new CustomerTree();
        List<CustomerSubTree> customers = new ArrayList<>();
        customers.add(populateCustomerSubTree(customer));
        customerTree.setCustomer(customers);
        customerResponse.setCustomers(customerTree);
        return customerResponse;
    }

    public OrderResponse populateOrderTree(List<OrderDTO> orders) {
        OrderResponse orderResponse = new OrderResponse();
        OrderTree orderTree = new OrderTree();
        if(!Objects.isNull(orders)) {
            List<OrderSubTree> orderSubTrees = populateOrderSubTrees(orders);
            orderTree.setOrder(orderSubTrees);
        }
        orderResponse.setOrders(orderTree);
        return orderResponse;
    }

    public OrderResponse populateOrderTree(@NonNull OrderDTO order) {
        return OrderResponse.builder()
                .orders(OrderTree.builder()
                        .order(populateOrderSubTree(order)).build()).build();
    }

    public ProductResponse populateProductTree(List<ProductDTO> products) {
        ProductResponse productResponse = new ProductResponse();
        ProductTree productTree = new ProductTree();
        List<ProductSubTree> productSubTrees = new ArrayList<>();
        products.forEach(productDTO -> {
            ProductSubTree productSubTree = new ProductSubTree();
            productSubTree.setId(productDTO.getId());
            productSubTree.setName(productDTO.getName());
            productSubTree.setPrice(productDTO.getPrice());
            productSubTrees.add(productSubTree);
            RatingTree ratingTree = new RatingTree();
            ratingTree.setRating(populateRatingSubTree(productDTO));
            productSubTree.setRatings(ratingTree);
        });
        productTree.setProduct(productSubTrees);
        productResponse.setProducts(productTree);
        return productResponse;
    }

    private List<OrderSubTree> populateOrderSubTrees(List<OrderDTO> orders) {
        List<OrderSubTree> orderSubTrees = new ArrayList<>();
        orders.forEach(orderDTO -> {
            OrderSubTree orderSubTree = new OrderSubTree();
            orderSubTree.setId(orderDTO.getId());
            orderSubTree.setPlacedOn(orderDTO.getPlacedOn());
            orderSubTree.setOrderStatus(orderDTO.getOrderStatus());
            orderSubTrees.add(orderSubTree);
            orderSubTree.setProducts(populateProductTree(orderDTO));
        });
        return orderSubTrees;
    }

    private List<OrderSubTree> populateOrderSubTree(OrderDTO order) {
        return List.of(OrderSubTree.builder().products(populateProductTree(order))
                .id(order.getId())
                .placedOn(order.getPlacedOn())
                .orderStatus(order.getOrderStatus()).build());
    }

    private ProductTree populateProductTree(OrderDTO orderDTO) {
        List<ProductDTO> productDTOS = orderDTO.getProducts();
        ProductTree productTree = new ProductTree();
        List<ProductSubTree> productSubTrees = new ArrayList<>();
        productDTOS.forEach(productDTO -> {
            ProductSubTree productSubTree = new ProductSubTree();
            productSubTree.setId(productDTO.getId());
            productSubTree.setName(productDTO.getName());
            productSubTree.setPrice(productDTO.getPrice());
            productSubTrees.add(productSubTree);
            productTree.setProduct(productSubTrees);
        });
        return productTree;
    }

    private List<RatingSubTree> populateRatingSubTree(ProductDTO productDTO) {
        List<RatingSubTree> ratingSubTrees = new ArrayList<>();
        List<RatingDTO> ratingDTOs = productDTO.getRatings();
        ratingDTOs.forEach(ratingDTO -> {
            RatingSubTree ratingSubTree = new RatingSubTree();
            ratingSubTree.setId(ratingDTO.getId());
            ratingSubTree.setRating(ratingDTO.getRate());
            ratingSubTrees.add(ratingSubTree);
            ReviewTree reviewTree = new ReviewTree();
            reviewTree.setReview(populateReviewSubTrees(ratingDTO));
            ratingSubTree.setReviews(reviewTree);
        });
        return ratingSubTrees;
    }

    private List<ReviewSubTree> populateReviewSubTrees(RatingDTO ratingDTO){
        List<ReviewSubTree> reviewSubTrees = new ArrayList<>();
        List<ReviewDTO> reviewDTOs = ratingDTO.getReviews();
        reviewDTOs.forEach(reviewDTO -> {
            ReviewSubTree reviewSubTree = new ReviewSubTree();
            reviewSubTree.setId(reviewDTO.getId());
            reviewSubTree.setTagLine(reviewDTO.getTagLine());
            reviewSubTree.setComment(reviewDTO.getComment());
            reviewSubTrees.add(reviewSubTree);
        });
        return reviewSubTrees;
    }

    private List<CustomerSubTree> populateCustomerSubTrees(List<CustomerDTO> customers){
        List<CustomerSubTree> customerSubTrees = new ArrayList<>();
        customers.forEach(customerDTO -> {
            CustomerSubTree customerSubTree = new CustomerSubTree();
            customerSubTree.setId(customerDTO.getId());
            customerSubTree.setSurname(customerDTO.getSurname());
            customerSubTree.setFirstName(customerDTO.getFirstName());
            customerSubTrees.add(customerSubTree);
            OrderTree orderTree = new OrderTree();
            orderTree.setOrder(populateOrderSubTrees(customerDTO.getOrders()));
            customerSubTree.setOrders(orderTree);
        });
        return customerSubTrees;
    }

    private CustomerSubTree populateCustomerSubTree(CustomerDTO customerDTO){
            CustomerSubTree customerSubTree = new CustomerSubTree();
            customerSubTree.setId(customerDTO.getId());
            customerSubTree.setSurname(customerDTO.getSurname());
            customerSubTree.setFirstName(customerDTO.getFirstName());
            OrderTree orderTree = new OrderTree();
            if(!Objects.isNull(customerDTO.getOrders())){
                orderTree.setOrder(populateOrderSubTrees(customerDTO.getOrders()));
            }
            customerSubTree.setOrders(orderTree);
        return customerSubTree;
    }
}
