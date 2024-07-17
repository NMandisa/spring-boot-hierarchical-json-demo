package za.co.mkhungo.response;

import lombok.*;
import za.co.mkhungo.response.node.ReviewTree;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private ReviewTree reviews;
}
