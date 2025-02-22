package za.co.mkhungo.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
//@DataJpaTest(showSql = false)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//@Rollback(value = false)
@ExtendWith(MockitoExtension.class) // Enables Mockito for JUnit 5
class CustomerRepositoryTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerFacade customerFacade;
    private Customer sampleCustomer;

    @BeforeEach
    void cleanup() {
        customerRepository.deleteAll(); // Ensure a clean state before each test
        sampleCustomer = new Customer("Thando", "Ngwenya");
    }

    @Test
    @DisplayName("Should throw exception when saving a null customer")
    void shouldThrowExceptionWhenSavingNullCustomer() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> customerFacade.save(null));
        verify(customerRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should save a customer successfully")
    void shouldSaveCustomerSuccessfully() {
        // Arrange;
        when(customerRepository.save(any(Customer.class))).thenReturn(sampleCustomer);

        // Act
        CustomerDTO savedCustomer = customerFacade.save(CustomerDTO.builder().firstName("Thando").surname("Ngwenya").build());

        // Assert
        assertNotNull(savedCustomer, "Saved customer should not be null");
        assertEquals("Thando", savedCustomer.getFirstName());
        verify(customerRepository, times(1)).save(sampleCustomer);
    }

    @Test
    @DisplayName("Should throw exception when customer is not found")
    void shouldThrowExceptionWhenCustomerNotFound() {
        // Arrange
        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerFacade.fetchCustomerById(999L));
        verify(customerRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Should delete customer by ID and return affected row count")
    void shouldDeleteCustomerByIdSuccessfully() throws CustomerNotFoundException {
        // Arrange
        when(customerRepository.deleteByIdAndCount(201L)).thenReturn(1);

        // Act
        int deletedRows = customerFacade.delete(201L);

        // Assert
        assertEquals(1, deletedRows, "One row should be affected");
        verify(customerRepository, times(1)).deleteByIdAndCount(201L);
    }

    @Test
    @DisplayName("Should return zero when deleting non-existent customer")
    void shouldReturnZeroWhenDeletingNonExistentCustomer() throws CustomerNotFoundException {
        // Arrange
        when(customerRepository.deleteByIdAndCount(999L)).thenReturn(0);

        // Act
        int deletedRows = customerFacade.delete(999L);

        // Assert
        assertEquals(0, deletedRows, "No rows should be affected");
        verify(customerRepository, times(1)).deleteByIdAndCount(999L);
    }

    @Test
    @DisplayName("Should retrieve customer by ID")
    void shouldRetrieveCustomerById() throws CustomerNotFoundException {
        // Arrange
        Customer customer = new Customer("Nomvula", "Mthembu");
        when(customerRepository.findById(200L)).thenReturn(Optional.of(customer));

        // Act
        CustomerDTO foundCustomer = customerFacade.fetchCustomerById(200L);

        // Assert
        assertNotNull(foundCustomer);
        log.info("Found customer with id {} name: {} surname : {}", foundCustomer.getId(), foundCustomer.getFirstName(), foundCustomer.getSurname());
        assertEquals("Nomvula", foundCustomer.getFirstName());
        verify(customerRepository, times(1)).findById(200L);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/customer_data.csv", numLinesToSkip = 1)
    @DisplayName("Should save multiple customers from CSV file")
    void shouldSaveAllCustomersFromCsv(String firstName, String surname) {
        // Arrange
        List<Customer> savedCustomers = customerRepository.saveAll(List.of(new Customer(firstName, surname)));

        assertNotNull(savedCustomers);
        assertEquals(10, savedCustomers.size());
        savedCustomers.forEach(customer -> {
            assertNotNull(customer.getId());
            //assertEquals(2, customer.getOrders().size());
        });
    }

    @Test
    @DisplayName("Should return all customers when fetching all")
    void shouldReturnAllCustomers() {
        // Arrange
        List<Customer> customers = List.of(
                new Customer("John", "Doe"),
                new Customer("Jane", "Doe")
        );
        when(customerRepository.findAll()).thenReturn(customers);

        // Act
        List<CustomerDTO> foundCustomers = customerFacade.fetchAllCustomers();

        log.info(" customers found {}",customers.size());

        // Assert
        assertNotNull(foundCustomers);
        assertNotEquals(0, customers.size(),"Should return all customers when fetching all");
        assertEquals(2, foundCustomers.size(), "Should return exactly 2 customers");
        verify(customerRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("Should fetch customers by filtering criteria")
    void shouldFetchCustomersByFilter() {
        // Arrange
        Set<Customer> filteredCustomers = Set.of(
                new Customer("Thabo", "Molefe"),
                new Customer("Tumi", "Mokgadi")
        );

        String searchTerm = "T";
        when(customerRepository.findCustomersByFilter(searchTerm)).thenReturn(filteredCustomers);

        // Act
        Set<CustomerDTO> result = customerFacade.fetchCustomersByFilter(searchTerm);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size(), "Should return 2 matching customers");
        verify(customerRepository, times(1)).findCustomersByFilter(searchTerm);
    }

    @Test
    @DisplayName("Should return empty list when no customers match filter")
    void shouldReturnEmptyListWhenNoCustomersMatchFilter() {
        // Arrange
        String searchTerm = "Xyz";
        when(customerRepository.findCustomersByFilter(searchTerm)).thenReturn(Set.of());

        // Act
        Set<CustomerDTO> result = customerFacade.fetchCustomersByFilter(searchTerm);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Customer list should be empty");
        verify(customerRepository, times(1)).findCustomersByFilter(searchTerm);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/customers_list.csv", numLinesToSkip = 1)
    @DisplayName("Should fetch customers with pagination")
    void shouldFetchCustomersWithPagination() {
        // Arrange
        List<Customer> customers = List.of(
                new Customer(
                        "Alice", "Smith"),
                new Customer("Bob", "Johnson")
        );
        Pageable pageable = PageRequest.of(0, 2);
        Page<Customer> pagedCustomers = new PageImpl<>(customers);

        when(customerRepository.findCustomersWithPagination(pageable)).thenReturn(pagedCustomers);

        // Act
        Page<CustomerDTO> result = customerFacade.fetchCustomersWithPagination(0, 2);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size(), "Should return 2 customers");
        verify(customerRepository, times(1)).findCustomersWithPagination(pageable);
    }

    @Test
    @DisplayName("Should return paginated customers when filtering")
    void shouldReturnPaginatedCustomersWhenFiltering() {
        // Arrange
        List<Customer> customers = List.of(
                new Customer( "Alice", "Smith"),
                new Customer( "Bob", "Johnson")
        );
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<Customer> customerPage = new PageImpl<>(customers, pageable, customers.size());

        when(customerRepository.findCustomersByFilter("Alice", pageable)).thenReturn(customerPage);

        // Act
        Page<CustomerDTO> result = customerFacade.fetchCustomersByFilter("Alice", 0, 2, "firstName", "ASC");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size(), "Should return 2 customers");
        verify(customerRepository, times(1)).findCustomersByFilter("Alice", pageable);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 2, firstName, ASC",
            "1, 3, surname, DESC"
    })
    @DisplayName("Should return customers with various pagination and sorting options")
    void shouldReturnCustomersWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        // Arrange
        List<Customer> customers = List.of(
                new Customer( "Alice", "Smith"),
                new Customer("Bob", "Johnson")
        );
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Customer> customerPage = new PageImpl<>(customers, pageable, customers.size());

        when(customerRepository.findCustomersByFilter(anyString(), eq(pageable))).thenReturn(customerPage);

        // Act
        Page<CustomerDTO> result = customerFacade.fetchCustomersByFilter("test", page, size, sortBy, sortDir);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size(), "Should return 2 customers");
        verify(customerRepository, times(1)).findCustomersByFilter(anyString(), eq(pageable));
    }

    @Test
    @DisplayName("Should return empty result when no customers match filter")
    void shouldReturnEmptyResultWhenNoCustomersMatch() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<Customer> emptyPage = Page.empty(pageable);

        when(customerRepository.findCustomersByFilter("NonExistent", pageable)).thenReturn(emptyPage);

        // Act
        Page<CustomerDTO> result = customerFacade.fetchCustomersByFilter("NonExistent", 0, 2, "firstName", "ASC");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Should return an empty page");
        verify(customerRepository, times(1)).findCustomersByFilter("NonExistent", pageable);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 2, firstName, ASC",
            "1, 3, surname, DESC"
    })
    @DisplayName("Should return paginated and sorted customers")
    void shouldReturnAllCustomersWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        // Arrange
        List<Customer> customers = List.of(
                new Customer("Alice", "Smith"),
                new Customer("Bob", "Johnson")
        );
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Customer> customerPage = new PageImpl<>(customers, pageable, customers.size());

        when(customerRepository.findAllCustomersWithPagination(pageable)).thenReturn(customerPage);

        // Act
        Page<CustomerDTO> result = customerFacade.fetchCustomersWithPagination(page, size, sortBy, sortDir);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size(), "Should return 2 customers");
        verify(customerRepository, times(1)).findAllCustomersWithPagination(pageable);
    }
}