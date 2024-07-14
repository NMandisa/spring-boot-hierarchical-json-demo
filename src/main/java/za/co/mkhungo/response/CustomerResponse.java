package za.co.mkhungo.response;

import lombok.*;
import za.co.mkhungo.response.node.CustomerTree;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private CustomerTree customers;
}
