package za.co.mkhungo.response.node.sub;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import za.co.mkhungo.model.enums.OrderStatus;
import za.co.mkhungo.response.node.ProductTree;

import java.time.LocalDateTime;

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
public class OrderSubTree extends RepresentationModel<OrderSubTree> {
    private Long id;
    private LocalDateTime placedOn;
    private OrderStatus orderStatus;
    private ProductTree products;
}
