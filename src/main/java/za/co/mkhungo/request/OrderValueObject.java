package za.co.mkhungo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import za.co.mkhungo.model.enums.OrderStatus;

import java.time.LocalDateTime;

/**
 * @author Noxolo.Mkhungo
 */
@Data
@Builder
@AllArgsConstructor
public class OrderValueObject {
    private Long id;
    private LocalDateTime placedOn;
    private OrderStatus orderStatus;
}