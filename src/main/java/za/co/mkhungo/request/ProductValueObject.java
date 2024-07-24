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
public class ProductValueObject {
    private Long id;
    private String name;
    private Double price;
}