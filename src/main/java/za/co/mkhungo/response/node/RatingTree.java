package za.co.mkhungo.response.node;

import lombok.*;
import za.co.mkhungo.response.node.sub.RatingSubTree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingTree {
    private Set<RatingSubTree> ratings = new HashSet<>();
}
