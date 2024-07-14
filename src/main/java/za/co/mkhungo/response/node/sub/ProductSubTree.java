package za.co.mkhungo.response.node.sub;

import lombok.*;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSubTree {
    private Long id;
    private String name;
    private Double price;
}
