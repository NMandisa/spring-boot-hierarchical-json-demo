package za.co.mkhungo.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.facade.OrderFacade;
import za.co.mkhungo.model.Customer;
import za.co.mkhungo.model.Order;
import za.co.mkhungo.repository.OrderRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.ArrayList;
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
     * @return
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orderDTOS=new ArrayList<>();
        List<Order> orders= orderRepository.findAll();
        orders.forEach(order -> {
            log.info("Order : " + order);
            OrderDTO orderDTO = MapperUtil.convertOrderModelToDto(order);
            orderDTOS.add(orderDTO);
        });
        return orderDTOS;
    }

    /**
     * @param order
     * @return
     */
    @Override
    public Long save(Order order) {
        return null;
    }

    /**
     * @param order
     * @param id
     * @return
     */
    @Override
    public int edit(Order order, Long id) {
        return 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int delete(Long id) {
        return 0;
    }
}
