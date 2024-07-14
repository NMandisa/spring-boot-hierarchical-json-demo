package za.co.mkhungo.response.node;

import lombok.*;
import za.co.mkhungo.response.node.sub.OrderSubTree;

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
public class OrderTree {
    private List<OrderSubTree> order=new ArrayList<>();
}
