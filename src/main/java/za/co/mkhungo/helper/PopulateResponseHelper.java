package za.co.mkhungo.helper;

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

    public CustomerResponse populateCustomerTree(List<CustomerDTO> customers) {
        CustomerResponse customerResponse=new CustomerResponse();
        CustomerTree customerTree = new CustomerTree();
        List<CustomerSubTree> customerSubTrees = new ArrayList<>();
        customers.forEach(customerDTO -> {
            CustomerSubTree customerSubTree = new CustomerSubTree();
            customerSubTree.setId(customerDTO.getId());
            customerSubTree.setSurname(customerDTO.getSurname());
            customerSubTree.setFirstName(customerDTO.getFirstName());
            customerSubTrees.add(customerSubTree);
            List<OrderDTO> orderDTOS = customerDTO.getOrders();
            List<OrderSubTree> orderSubTrees = new ArrayList<>();
            OrderTree orderTree = new OrderTree();
            orderDTOS.forEach(orderDTO -> {
                OrderSubTree orderSubTree = new OrderSubTree();
                orderSubTree.setId(orderDTO.getId());
                orderSubTree.setOrderStatus(orderDTO.getOrderStatus());
                orderSubTree.setPlacedOn(orderDTO.getPlacedOn());
                orderSubTrees.add(orderSubTree);
                List<ProductDTO> productDTOS = orderDTO.getProducts();
                List<ProductSubTree> productSubTrees = new ArrayList<>();
                ProductTree productTree = new ProductTree();
                productDTOS.forEach(productDTO -> {
                    ProductSubTree productSubTree = new ProductSubTree();
                    productSubTree.setId(productDTO.getId());
                    productSubTree.setName(productDTO.getName());
                    productSubTree.setPrice(productDTO.getPrice());
                    productSubTrees.add(productSubTree);
                });
                productTree.setProduct(productSubTrees);
                orderSubTree.setProducts(productTree);
            });
            orderTree.setOrder(orderSubTrees);
            customerSubTree.setOrders(orderTree);
        });
        customerTree.setCustomer(customerSubTrees);
        customerResponse.setCustomers(customerTree);
        return customerResponse;
    }
    public OrderResponse populateOrderTree(List<OrderDTO> orders) {
        OrderResponse orderResponse = new OrderResponse();
        OrderTree orderTree = new OrderTree();
        List<OrderSubTree> orderSubTrees = new ArrayList<>();
        orders.forEach(orderDTO -> {
            OrderSubTree orderSubTree = new OrderSubTree();
            orderSubTree.setId(orderDTO.getId());
            orderSubTree.setPlacedOn(orderDTO.getPlacedOn());
            orderSubTree.setOrderStatus(orderDTO.getOrderStatus());
            orderSubTrees.add(orderSubTree);
            List<ProductDTO> productDTOS = orderDTO.getProducts();
            ProductTree productTree = new ProductTree();
            List<ProductSubTree> productSubTrees = new ArrayList<>();
            productDTOS.forEach(productDTO -> {
                ProductSubTree productSubTree = new ProductSubTree();
                productSubTree.setId(productDTO.getId());
                productSubTree.setName(productDTO.getName());
                productSubTree.setPrice(productDTO.getPrice());
                productSubTrees.add(productSubTree);
            });
            productTree.setProduct(productSubTrees);
            orderSubTree.setProducts(productTree);
        });
        orderTree.setOrder(orderSubTrees);
        orderResponse.setOrders(orderTree);
        return orderResponse;
    }
    public ProductResponse populateProductTree(List<ProductDTO> products) {
        ProductResponse productResponse=new ProductResponse();
        ProductTree productTree=new ProductTree();
        List<ProductSubTree> productSubTrees=new ArrayList<>();
        products.forEach(productDTO -> {
            ProductSubTree productSubTree=new ProductSubTree();
            productSubTree.setId(productDTO.getId());
            productSubTree.setName(productDTO.getName());
            productSubTree.setPrice(productDTO.getPrice());
            productSubTrees.add(productSubTree);
            List<RatingSubTree> ratingSubTrees = new ArrayList<>();
            RatingTree ratingTree=new RatingTree();
            List<RatingDTO> ratingDTOs=productDTO.getRatings();
            ratingDTOs.forEach(ratingDTO -> {
                RatingSubTree ratingSubTree=new RatingSubTree();
                ratingSubTree.setRating(ratingDTO.getRate());
                ratingSubTrees.add(ratingSubTree);
                ReviewTree reviewTree = new ReviewTree();
                List<ReviewDTO> reviewDTOs = ratingDTO.getReviews();
                List<ReviewSubTree> reviewSubTrees=new ArrayList<>();
                reviewDTOs.forEach(reviewDTO -> {
                    ReviewSubTree reviewSubTree=new ReviewSubTree();
                    reviewSubTree.setTagLine(reviewDTO.getTagLine());
                    reviewSubTree.setComment(reviewDTO.getComment());
                    reviewSubTrees.add(reviewSubTree);
                });
                reviewTree.setReview(reviewSubTrees);
                ratingSubTree.setReviews(reviewTree);
            });
            ratingTree.setRating(ratingSubTrees);
            productSubTree.setRatings(ratingTree);
        });
        productTree.setProduct(productSubTrees);
        productResponse.setProducts(productTree);
        return productResponse;
    }
}
