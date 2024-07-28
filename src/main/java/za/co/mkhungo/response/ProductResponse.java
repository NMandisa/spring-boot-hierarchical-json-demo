package za.co.mkhungo.response;

import lombok.*;
import za.co.mkhungo.response.node.ProductTree;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse{
    private ProductTree products;
}
