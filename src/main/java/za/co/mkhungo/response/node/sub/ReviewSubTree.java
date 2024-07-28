package za.co.mkhungo.response.node.sub;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

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
public class ReviewSubTree extends RepresentationModel<ReviewSubTree> {
    private Long id;
    private String tagLine;
    private String comment;
}
