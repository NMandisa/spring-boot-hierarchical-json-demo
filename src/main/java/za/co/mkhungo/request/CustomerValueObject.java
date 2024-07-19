package za.co.mkhungo.request;

import lombok.*;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Builder
@AllArgsConstructor
public class CustomerValueObject {
    private String firstName;
    private String surname;
}