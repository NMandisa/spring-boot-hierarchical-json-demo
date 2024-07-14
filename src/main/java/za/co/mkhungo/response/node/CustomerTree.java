package za.co.mkhungo.response.node;

import lombok.*;
import za.co.mkhungo.response.node.sub.CustomerSubTree;

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
public class CustomerTree {
    private List<CustomerSubTree> customer=new ArrayList<>();
}
