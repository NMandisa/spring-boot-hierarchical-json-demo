package za.co.mkhungo.response;

import lombok.*;
import za.co.mkhungo.response.node.RatingTree;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    private RatingTree ratingTree;
}
