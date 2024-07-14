package za.co.mkhungo.response.node.sub;

import lombok.*;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingSubTree {
    private Long id;
    private int rating;
}
