package za.co.mkhungo.response.node;

import lombok.*;
import za.co.mkhungo.response.node.sub.ProductSubTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductTree {
    private List<ProductSubTree> product = new ArrayList<>();
}
