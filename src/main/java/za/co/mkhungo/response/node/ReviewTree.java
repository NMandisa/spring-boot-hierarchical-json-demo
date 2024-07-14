package za.co.mkhungo.response.node;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import za.co.mkhungo.response.node.sub.ReviewSubTree;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
public class ReviewTree {
    private Set<ReviewSubTree> reviews = new HashSet<>();
}
