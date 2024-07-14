package za.co.mkhungo.dto;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Set<RatingDTO> ratings= new HashSet<>();
    private OrderDTO orderDTO;
    public ProductDTO(){
        super();
    }
    public void addRating(RatingDTO rating){
        ratings.add(rating);
        rating.setProduct(this);
    }
    public void removeRating(RatingDTO ratingDTO){
        ratings.remove(ratingDTO);
        ratingDTO.setProduct(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductDTO other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
