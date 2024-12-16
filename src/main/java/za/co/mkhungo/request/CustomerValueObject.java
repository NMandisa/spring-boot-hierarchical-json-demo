package za.co.mkhungo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Builder
@AllArgsConstructor
public class CustomerValueObject {
    @NotBlank(message ="First name cannot be blank")
    @Size(message = "First name cannot be more then 50 characters",min = 1, max = 50)
    private String firstName;
    @NotBlank(message ="Surname cannot be blank")
    @Size(message = "Surname cannot be more then 50 characters",min = 1, max = 50)
    private String surname;
}