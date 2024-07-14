package za.co.mkhungo.response.node.sub;

import lombok.*;
import za.co.mkhungo.dto.RatingDTO;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSubTree {
    private Long id;
    private String tagLine;
    private String comment;
    private RatingDTO rating;
}
