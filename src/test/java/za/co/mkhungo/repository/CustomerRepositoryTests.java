package za.co.mkhungo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import za.co.mkhungo.model.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Noxolo.Mkhungo
 */

@Transactional
@DataJpaTest
@Rollback(false)
class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testDeleteByIdAndCount() {
        Customer customer = new Customer("Sibongile","Zulu");
        Customer savedCustomer = customerRepository.save(customer);

        int rowsAffected = customerRepository.deleteByIdAndCount(savedCustomer.getId());

        assertEquals(1, rowsAffected);
        assertFalse(customerRepository.existsById(savedCustomer.getId()));
    }


    @Test
    void testSaveCustomer() {
        Customer customer = new Customer("Thando","Ngwenya");
        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer);
        assertEquals("Thando", savedCustomer.getFirstName());
    }


    @Test
    void testFindById() {
        Customer customer = new Customer("Nomvula","Mthembu");
        Customer savedCustomer = customerRepository.save(customer);

        Customer foundCustomer = customerRepository.findById(savedCustomer.getId()).orElse(null);

        assertNotNull(foundCustomer);
        assertEquals("Nomvula", foundCustomer.getFirstName());
    }


    @Test
    void testFindAll() {
        List<Customer> customers = customerRepository.findAll();
        assertNotEquals(0, customers.size());
    }

    @Test
    void testSaveAllCustomers() {
        List<Customer> customers = List.of(
                new Customer("Mandisa", "Mkhungo"),
                new Customer("Sbahle", "Ntuli"),
                new Customer("Ntobeko", "Dlamini"),
                new Customer("Nomvula", "Gumede"),
                new Customer("Sipho", "Sibanda"),
                new Customer("Ayanda", "Zulu"),
                new Customer("Nokuthula", "Mbatha"),
                new Customer("Thando", "Ngwenya"),
                new Customer("Sibongile", "Mkhize"),
                new Customer("Nomonde", "Msimang")
        );

        /*customers.forEach(customer -> {
            customer.addOrder(createOrder("Order1"));
            customer.addOrder(createOrder("Order2"));
        });*/

        List<Customer> savedCustomers = customerRepository.saveAll(customers);

        assertNotNull(savedCustomers);
        assertEquals(10, savedCustomers.size());
        savedCustomers.forEach(customer -> {
            assertNotNull(customer.getId());
            //assertEquals(2, customer.getOrders().size());
        });
    }
}
