package za.co.mkhungo.response.node.sub;

import lombok.*;
import za.co.mkhungo.model.enums.OrderStatus;
import za.co.mkhungo.response.node.ProductTree;

import java.time.LocalDateTime;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSubTree {
    private Long id;
    private LocalDateTime placedOn;
    private OrderStatus orderStatus;
    private ProductTree products;
}
