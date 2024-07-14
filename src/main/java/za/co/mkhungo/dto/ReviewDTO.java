package za.co.mkhungo.dto;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String tagLine;
    private String comment;
    private RatingDTO rating;
    public ReviewDTO(){
        super();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ReviewDTO other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
