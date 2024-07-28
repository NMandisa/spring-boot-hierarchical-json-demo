package za.co.mkhungo.response.node.sub;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import za.co.mkhungo.response.node.ReviewTree;

/**
 * @author Noxolo.Mkhungo
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingSubTree extends RepresentationModel<RatingSubTree> {
    private Long id;
    private int rating;
    private ReviewTree reviews;
}
