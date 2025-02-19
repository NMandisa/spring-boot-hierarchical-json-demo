package za.co.mkhungo.response.node.sub;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import za.co.mkhungo.response.node.OrderTree;

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
public class CustomerSubTree extends RepresentationModel<CustomerSubTree> {
    private Long id;
    private String firstName;
    private String surname;
    private OrderTree orders;

}
