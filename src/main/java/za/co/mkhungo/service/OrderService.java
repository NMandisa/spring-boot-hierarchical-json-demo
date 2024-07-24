package za.co.mkhungo.service;

import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.exception.OrderNotFoundException;
import za.co.mkhungo.response.OrderResponse;

/**
 * @author Noxolo.Mkhungo
 */
public interface OrderService {
    OrderResponse getAllOrders();
    OrderResponse getOrderById(Long id) throws OrderNotFoundException;
    OrderResponse save(OrderDTO orderDTO);
    OrderResponse edit(OrderDTO orderDTO,Long id);
    int delete(Long id);
}
