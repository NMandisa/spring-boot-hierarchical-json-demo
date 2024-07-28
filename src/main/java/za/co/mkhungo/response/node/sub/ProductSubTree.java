package za.co.mkhungo.response.node.sub;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import za.co.mkhungo.response.node.RatingTree;

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
public class ProductSubTree extends RepresentationModel<ProductSubTree> {
    private Long id;
    private String name;
    private Double price;
    private RatingTree ratings;
}
