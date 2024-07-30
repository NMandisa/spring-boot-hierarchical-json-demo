package za.co.mkhungo.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.facade.impl.DefaultCustomerFacade;
import za.co.mkhungo.model.Customer;
import za.co.mkhungo.repository.CustomerRepository;
import za.co.mkhungo.utils.MapperUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Noxolo.Mkhungo
 */
class CustomerFacadeTests {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private DefaultCustomerFacade customerFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize the ModelMapper
        //MapperUtil.modelMapper = new ModelMapper();
    }

@Test
void testFetchAllCustomers() {
    Customer customer = new Customer("Mandisa", "Mkhungo");
    customer.setId(1L);
    when(customerRepository.findAll()).thenReturn(List.of(customer));

    List<CustomerDTO> customers = customerFacade.fetchAllCustomers();

    assertNotNull(customers);
    assertEquals(1, customers.size());
    assertEquals("Mandisa", customers.get(0).getFirstName());
}

@Test
void testFetchCustomerById() throws CustomerNotFoundException {
    Customer customer = new Customer("Mandisa", "Mkhungo");
    customer.setId(1L);
    when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

    CustomerDTO customerDTO = customerFacade.fetchCustomerById(1L);

    assertNotNull(customerDTO);
    assertEquals("Mandisa", customerDTO.getFirstName());
}

@Test
void testSaveCustomer() {
    CustomerDTO customerDTO = CustomerDTO.builder()
            .id(1L)
            .firstName("Mandisa")
            .surname("Mkhungo")
            .build();

    Customer customer = new Customer("Mandisa", "Mkhungo");
    customer.setId(1L);
    when(customerRepository.save(any(Customer.class))).thenReturn(customer);
    CustomerDTO savedCustomerDTO = customerFacade.save(customerDTO);

    assertNotNull(savedCustomerDTO);
    assertEquals("Mandisa", savedCustomerDTO.getFirstName());
}

@Test
void testEditCustomer() throws CustomerNotFoundException, CustomerException {
    Customer existingCustomer = new Customer("Mandisa", "Mkhungo");
    existingCustomer.setId(1L);

    Customer updatedCustomer = new Customer("Sibongile", "Zulu");
    updatedCustomer.setId(1L);

    CustomerDTO customerDTO = CustomerDTO.builder()
            .firstName("Sibongile")
            .surname("Zulu")
            .build();

    when(customerRepository.findById(anyLong())).thenReturn(Optional.of(existingCustomer));
    when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

    CustomerDTO editedCustomerDTO = customerFacade.edit(customerDTO, 1L);

    assertNotNull(editedCustomerDTO);
    assertEquals("Sibongile", editedCustomerDTO.getFirstName());
}

@Test
void testDeleteCustomer() throws CustomerNotFoundException {
    when(customerRepository.existsById(anyLong())).thenReturn(true);
    when(customerRepository.deleteByIdAndCount(anyLong())).thenReturn(1);

    int rowsAffected = customerFacade.delete(1L);

    assertEquals(1, rowsAffected);
}
}
