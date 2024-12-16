package za.co.mkhungo.utils;

import org.modelmapper.ModelMapper;
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

    private static final ModelMapper modelMapper = new ModelMapper();

    private MapperUtil(){}

    public static ProductDTO convertProductModelToDto(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }
    public static Product convertProductDtoToModel (ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }
    public static OrderDTO convertOrderModelToDto (Order order){
        return modelMapper.map(order, OrderDTO.class);
    }
    public static Order convertOrderDtoToModel (OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }
    public static CustomerDTO convertCustomerModelToDto (Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
    public static Customer convertCustomerDtoToModel (CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }
    public static ReviewDTO convertReviewModelToDto (Review review){
        return modelMapper.map(review, ReviewDTO.class);
    }
    public static Review convertReviewDtoToModel (ReviewDTO reviewDTO){
        return modelMapper.map(reviewDTO, Review.class);
    }
    public static RatingDTO convertRatingModelToDto (Rating rating){
        return modelMapper.map(rating, RatingDTO.class);
    }
    public static Rating convertRatingDtoToModel (RatingDTO ratingDTO){
        return modelMapper.map(ratingDTO, Rating.class);
    }
}
