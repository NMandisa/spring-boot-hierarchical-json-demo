package za.co.mkhungo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import za.co.mkhungo.dto.OrderDTO;
import za.co.mkhungo.exception.OrderNotFoundException;
import za.co.mkhungo.facade.OrderFacade;
import za.co.mkhungo.helper.OrderTreePopulator;
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
    private final OrderTreePopulator orderTreePopulator;
    @Autowired
    public DefaultOrderService(@Qualifier("defaultOrderFacade") OrderFacade orderFacade,OrderTreePopulator orderTreePopulator){
        this.orderFacade=orderFacade;
        this.orderTreePopulator=orderTreePopulator;
    }

    /**
     * @return OrderResponse
     */
    @Override
    public OrderResponse getAllOrders() {
        return OrderResponse.builder()
                .orders(orderTreePopulator.populateOrderTree(orderFacade.fetchAllOrders()))
                .build();
    }

    /**
     * @param id order id
     * @return OrderResponse
     */
    @Override
    public OrderResponse getOrderById(Long id) throws OrderNotFoundException {
        return OrderResponse.builder()
                .orders(orderTreePopulator.populateOrderTree(List.of(orderFacade.fetchOrderById(id)))).build();
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
