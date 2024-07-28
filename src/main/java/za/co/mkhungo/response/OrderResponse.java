package za.co.mkhungo.response;

import lombok.*;
import za.co.mkhungo.response.node.OrderTree;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse{
    private OrderTree orders;
}
