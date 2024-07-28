package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.exception.OrderNotFoundException;
import za.co.mkhungo.facade.OrderFacade;
import za.co.mkhungo.model.Order;
import za.co.mkhungo.repository.OrderRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Component
public class DefaultOrderFacade implements OrderFacade {
    private final OrderRepository orderRepository;
    public DefaultOrderFacade(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }

    /**
     * @return List<OrderDTO> orders
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .peek(order -> log.debug("Order : {}", order))
                .map(MapperUtil::convertOrderModelToDto).toList();
    }

    /**
     * @param id order id
     * @return OrderDTO order
     */
    @Override
    public OrderDTO getOrderById(Long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        log.debug("Order : {} class {} -->", order, OrderFacade.class);
        return MapperUtil.convertOrderModelToDto(order);
    }

    /**
     * @param orderDTO order dto
     * @return OrderDTO
     */
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        return null;
    }

    /**
     * @param orderDTO order dto
     * @param id order id
     * @return OrderDTO
     */
    @Override
    public OrderDTO edit(OrderDTO orderDTO, Long id) {
        return null;
    }

    /**
     * @param id order id
     * @return integer value of row affect
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
