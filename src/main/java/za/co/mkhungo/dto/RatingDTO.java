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
public class RatingDTO {
    private Long id;
    private int rating;
    private Set<ReviewDTO> reviews= new HashSet<>();
    private ProductDTO product;
    public RatingDTO(){
        super();
    }
    public void addReview(ReviewDTO review){
        reviews.add(review);
        review.setRating(this);
    }
    public void removeReview(ReviewDTO review){
        reviews.remove(review);
        review.setRating(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RatingDTO other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
