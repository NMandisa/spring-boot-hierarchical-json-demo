package za.co.mkhungo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Builder
@AllArgsConstructor
public class ReviewValueObject {
    private Long id;
    private String tagLine;
    private String comment;
}
