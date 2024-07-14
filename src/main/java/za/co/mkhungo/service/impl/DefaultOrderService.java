package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.facade.OrderFacade;
import za.co.mkhungo.model.Order;
import za.co.mkhungo.service.OrderService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultOrderService implements OrderService {
    private final OrderFacade orderFacade;
    @Autowired
    public DefaultOrderService(@Qualifier("defaultOrderFacade") OrderFacade orderFacade){
        this.orderFacade=orderFacade;
    }

    /**
     * @return
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderFacade.getAllOrders();
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
