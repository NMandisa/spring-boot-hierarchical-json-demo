package za.co.mkhungo.utils;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.*;
import za.co.mkhungo.model.*;

/**
 * @author Noxolo.Mkhungo
 */
@Component
@Scope("prototype")
public final class MapperUtil {

    @Getter
    private static ModelMapper modelMapper;
    @Autowired
    private MapperUtil(ModelMapper modelMapper) {
        MapperUtil.setModelMapper(modelMapper);
    }

    private MapperUtil(){}

    public static ProductDTO convertProductModelToDto(Product product){
        return getModelMapper().map(product, ProductDTO.class);
    }
    public static Product convertProductDtoToModel (ProductDTO productDTO){
        return getModelMapper().map(productDTO, Product.class);
    }
    public static OrderDTO convertOrderModelToDto (Order order){
        return getModelMapper().map(order, OrderDTO.class);
    }
    public static Order convertOrderDtoToModel (OrderDTO orderDTO){
        return getModelMapper().map(orderDTO, Order.class);
    }
    public static CustomerDTO convertCustomerModelToDto (Customer customer){
        return getModelMapper().map(customer, CustomerDTO.class);
    }
    public static Customer convertCustomerDtoToModel (CustomerDTO customerDTO){
        return getModelMapper().map(customerDTO, Customer.class);
    }
    public static ReviewDTO convertReviewModelToDto (Review review){
        return getModelMapper().map(review, ReviewDTO.class);
    }
    public static Review convertReviewDtoToModel (ReviewDTO reviewDTO){
        return getModelMapper().map(reviewDTO, Review.class);
    }
    public static RatingDTO convertRatingModelToDto (Rating rating){
        return getModelMapper().map(rating, RatingDTO.class);
    }
    public static Rating convertRatingDtoToModel (RatingDTO ratingDTO){
        return getModelMapper().map(ratingDTO, Rating.class);
    }

    public static void setModelMapper(ModelMapper modelMapper) {
        MapperUtil.modelMapper = modelMapper;
    }
}
