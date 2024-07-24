package za.co.mkhungo.facade;

import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.exception.OrderNotFoundException;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface OrderFacade {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id) throws OrderNotFoundException;
    OrderDTO save(OrderDTO order);
    OrderDTO edit(OrderDTO order,Long id);
    int delete(Long id);
}
