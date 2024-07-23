package za.co.mkhungo.facade;

import za.co.mkhungo.dto.OrderDTO;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface OrderFacade {
    List<OrderDTO> getAllOrders();
    OrderDTO save(OrderDTO order);
    OrderDTO edit(OrderDTO order,Long id);
    int delete(Long id);
}
