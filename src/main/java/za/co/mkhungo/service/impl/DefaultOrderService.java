package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.facade.OrderFacade;
import za.co.mkhungo.helper.PopulateResponseHelper;
import za.co.mkhungo.response.OrderResponse;
import za.co.mkhungo.service.OrderService;

import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@Service
public class DefaultOrderService implements OrderService {
    private final OrderFacade orderFacade;
    private final PopulateResponseHelper populateResponseHelper;
    @Autowired
    public DefaultOrderService(@Qualifier("defaultOrderFacade") OrderFacade orderFacade,PopulateResponseHelper populateResponseHelper){
        this.orderFacade=orderFacade;
        this.populateResponseHelper=populateResponseHelper;
    }

    /**
     * @return OrderResponse
     */
    @Override
    public OrderResponse getAllOrders() {
        List<OrderDTO> orders = orderFacade.getAllOrders();
        return populateResponseHelper.populateOrderTree(orders);
    }

    /**
     * @param order order dto
     * @return OrderResponse
     */
    @Override
    public OrderResponse save(OrderDTO order) {
        return null;
    }

    /**
     * @param order order dto
     * @param id order id
     * @return OrderResponse
     */
    @Override
    public OrderResponse edit(OrderDTO order, Long id) {
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
