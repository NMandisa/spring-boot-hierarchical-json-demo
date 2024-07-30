package za.co.mkhungo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Noxolo.Mkhungo
 */
class CustomerTests {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("John","Doe");
    }

    @Test
    void testAddOrder() {
        Order order = new Order();
        customer.addOrder(order);

        assertEquals(1, customer.getOrders().size());
        assertEquals(customer, order.getCustomer());
    }

    @Test
    void testRemoveOrder() {
        Order order = new Order();
        order.setId(1L);
        customer.addOrder(order);
        customer.removeOrder(order);

        assertEquals(0, customer.getOrders().size());
        assertNull(order.getCustomer());
    }

    @Test
    void testHashCode() {
        int hashCode = customer.hashCode();
        assertNotNull(hashCode);
    }

    @Test
    void testEquals() {
        Customer anotherCustomer = new Customer("John","Doe");
        assertEquals(customer, anotherCustomer);
    }


}
