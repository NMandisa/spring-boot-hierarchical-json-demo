package za.co.mkhungo.response.node.sub;

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
public class CustomerSubTree {

    private Long id;
    private String firstName;
    private String surname;
    private OrderTree orders;

}
