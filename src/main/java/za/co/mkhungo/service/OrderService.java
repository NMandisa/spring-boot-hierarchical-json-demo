package za.co.mkhungo.service;

import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.model.Order;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
public interface OrderService {
    List<OrderDTO> getAllOrders();
    Long save(Order order);
    int edit(Order order,Long id);
    int delete(Long id);
}
