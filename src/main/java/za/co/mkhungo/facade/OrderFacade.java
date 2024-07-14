package za.co.mkhungo.facade;

import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.model.Order;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface OrderFacade {
    List<OrderDTO> getAllOrders();
    Long save(Order order);
    int edit(Order order,Long id);
    int delete(Long id);
}
