package za.co.mkhungo.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import za.co.mkhungo.dto.*;
import za.co.mkhungo.model.*;
import za.co.mkhungo.model.enums.OrderStatus;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Noxolo.Mkhungo
 */
class MapperUtilTests {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testConvertProductModelToDto() {
        Product product = new Product();
        product.setId(300L);
        product.setName("Product1");
        product.setPrice(100.0);

        ProductDTO productDTO = MapperUtil.convertProductModelToDto(product);

        assertNotNull(productDTO);
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice(), 0.01);
    }

    @Test
    void testConvertProductDtoToModel() {
        ProductDTO productDTO = ProductDTO.builder().id(303L).name("Product1").price(242.23).build();
        Product product = MapperUtil.convertProductDtoToModel(productDTO);

        assertNotNull(product);
        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getPrice(), product.getPrice(), 0.01);
    }

    @Test
    void testConvertOrderModelToDto() {
        Order order = new Order();
        order.setId(503L);
        order.setOrderStatus(OrderStatus.COMPLETED);

        OrderDTO orderDTO = MapperUtil.convertOrderModelToDto(order);

        assertNotNull(orderDTO);
        assertEquals(order.getId(), orderDTO.getId());
        assertEquals(order.getOrderStatus(), orderDTO.getOrderStatus());
    }

    @Test
    void testConvertOrderDtoToModel() {
        OrderDTO orderDTO = OrderDTO.builder().id(500L).orderStatus(OrderStatus.SHIPPED).build();
        Order order = MapperUtil.convertOrderDtoToModel(orderDTO);

        assertNotNull(order);
        assertEquals(orderDTO.getId(), order.getId());
        assertEquals(orderDTO.getOrderStatus(), order.getOrderStatus());
    }

    @Test
    void testConvertCustomerModelToDto() {
        Customer customer = new Customer("Mandisa", "Mkhungo");
        customer.setId(200L);

        CustomerDTO customerDTO = MapperUtil.convertCustomerModelToDto(customer);

        assertNotNull(customerDTO);
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getSurname(), customerDTO.getSurname());
    }

    @Test
    void testConvertCustomerDtoToModel() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(200L).firstName("Mandisa")
                .surname("Mkhungo").build();

        Customer customer = MapperUtil.convertCustomerDtoToModel(customerDTO);

        assertNotNull(customer);
        assertEquals(customerDTO.getId(), customer.getId());
        assertEquals(customerDTO.getFirstName(), customer.getFirstName());
        assertEquals(customerDTO.getSurname(), customer.getSurname());
    }


}
