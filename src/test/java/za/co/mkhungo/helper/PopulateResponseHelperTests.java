package za.co.mkhungo.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import za.co.mkhungo.dto.*;
import za.co.mkhungo.exception.*;
import za.co.mkhungo.model.enums.OrderStatus;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.response.OrderResponse;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Noxolo.Mkhungo
 */
class PopulateResponseHelperTests {
    @InjectMocks
    private PopulateResponseHelper populateResponseHelper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPopulateCustomerResponseWithList() {
        CustomerDTO customerDTO = CustomerDTO.builder().id(100L).firstName("Mandisa").surname("Mkhungo").build();
        customerDTO.setOrders(List.of(OrderDTO.builder().placedOn(LocalDateTime.now()).orderStatus(OrderStatus.RETURNED).build()));
        List<CustomerDTO> customerList = List.of(customerDTO);

        CustomerResponse response = populateResponseHelper.populateCustomerResponse(customerList);

        assertNotNull(response);
        assertEquals(1, response.getCustomers().getCustomer().size());
        assertEquals("Mandisa", response.getCustomers().getCustomer().getFirst().getFirstName());
    }

    @Test
    void testPopulateCustomerResponseWithSingleCustomer() throws CustomerNotFoundException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setOrderStatus(OrderStatus.COMPLETED);

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("Mandisa")
                .surname("Mkhungo")
                .orders(List.of(orderDTO)) // Initialize orders list
                .build();

        CustomerResponse response = populateResponseHelper.populateCustomerResponse(customerDTO);

        assertNotNull(response);
        assertEquals(1, response.getCustomers().getCustomer().size());
        assertEquals("Mandisa", response.getCustomers().getCustomer().get(0).getFirstName());
    }

    @Test
    void testPopulateOrderResponseWithList() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setOrderStatus(OrderStatus.PROCESSING);

        List<OrderDTO> orderList = List.of(orderDTO);

        OrderResponse response = populateResponseHelper.populateOrderResponse(orderList);

        assertNotNull(response);
        assertEquals(1, response.getOrders().getOrder().size());
        assertEquals(OrderStatus.PROCESSING, response.getOrders().getOrder().get(0).getOrderStatus());
    }

    @Test
    void testPopulateOrderResponseWithSingleOrder() throws OrderNotFoundException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setOrderStatus(OrderStatus.RETURNED);

        OrderResponse response = populateResponseHelper.populateOrderResponse(orderDTO);

        assertNotNull(response);
        assertEquals(1, response.getOrders().getOrder().size());
        assertEquals(OrderStatus.RETURNED, response.getOrders().getOrder().get(0).getOrderStatus());
    }


}
