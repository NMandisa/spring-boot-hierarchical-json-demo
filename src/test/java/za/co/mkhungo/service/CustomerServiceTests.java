package za.co.mkhungo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.helper.CustomerTreePopulator;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.service.impl.DefaultCustomerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Noxolo.Mkhungo
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {

    @Mock
    private CustomerFacade customerFacade;

    //@Mock
    //private PopulateResponseHelper populateResponseHelper;
    @Mock
    private CustomerTreePopulator customerTreePopulator;

    @InjectMocks
    private DefaultCustomerService customerService;

    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("John");
        customerDTO.setSurname("Doe");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should throw CustomerException when fetching all customers and database is down")
    void shouldThrowException_whenDatabaseFailsFetchingAllCustomers() {
        when(customerFacade.fetchAllCustomers()).thenThrow(new RuntimeException("Database error"));

        assertThrows(CustomerException.class, () -> customerService.getAllCustomers());
    }

    @Test
    @DisplayName("Should return all customers when fetching all customers")
    void shouldReturnAllCustomers_whenFetchingAllCustomers() {
        when(customerFacade.fetchAllCustomers()).thenReturn(List.of(customerDTO));
        when(customerTreePopulator.populateCustomerTree(any())).thenReturn(new CustomerResponse().getCustomers());

        CustomerResponse response = customerService.getAllCustomers();

        // Assert
        assertNotNull(response, "Response should not be null");
        verify(customerFacade, times(1)).fetchAllCustomers();
    }

    @Test
    @DisplayName("Should return customer when fetching by ID")
    void shouldReturnCustomer_whenFetchingById() throws CustomerNotFoundException {
        when(customerFacade.fetchCustomerById(1L)).thenReturn(customerDTO);
        when(customerTreePopulator.populateCustomerTree(any())).thenReturn(new CustomerResponse().getCustomers());

        CustomerResponse response = customerService.getCustomerById(1L);

        // Assert
        assertNotNull(response, "Response should not be null");
        verify(customerFacade, times(1)).fetchCustomerById(1L);
    }

    @Test
    @DisplayName("Should throw exception when customer is not found")
    void shouldThrowException_whenCustomerNotFound() throws CustomerNotFoundException {
        when(customerFacade.fetchCustomerById(1L)).thenThrow(new CustomerNotFoundException("Customer not found"));

        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
    }

    @Test
    @DisplayName("Should save customer successfully")
    void shouldSaveCustomerSuccessfully() throws CustomerException {
        when(customerFacade.save(any(CustomerDTO.class))).thenReturn(customerDTO);
        when(customerTreePopulator.populateCustomerTree(any())).thenReturn(new CustomerResponse().getCustomers());

        CustomerResponse response = customerService.save(customerDTO);

        // Assert
        assertNotNull(response, "Response should not be null");
        verify(customerFacade, times(1)).save(customerDTO);
    }

    @Test
    @DisplayName("Should throw exception when saving fails")
    void shouldThrowException_whenSavingFails() {
        when(customerFacade.save(any(CustomerDTO.class))).thenThrow(new CustomerException("Save failed"));

        // Act & Assert
        assertThrows(CustomerException.class, () -> customerService.save(customerDTO));
        verify(customerFacade, times(1)).save(customerDTO);
    }

    @Test
    @DisplayName("Should edit customer successfully")
    void shouldEditCustomerSuccessfully() throws CustomerException, CustomerNotFoundException {
        when(customerFacade.edit(any(CustomerDTO.class), eq(1L))).thenReturn(customerDTO);
        when(customerTreePopulator.populateCustomerTree(any())).thenReturn(new CustomerResponse().getCustomers());

        CustomerResponse response = customerService.edit(customerDTO, 1L);

        // Assert
        assertNotNull(response, "Response should not be null");
        verify(customerFacade, times(1)).edit(customerDTO, 1L);
    }

    @Test
    @DisplayName("Should throw exception when editing fails")
    void shouldThrowException_whenEditingFails() throws CustomerNotFoundException {
        when(customerFacade.edit(any(CustomerDTO.class), eq(1L))).thenThrow(new CustomerException("Edit failed"));

        // Act & Assert
        assertThrows(CustomerException.class, () -> customerService.edit(customerDTO, 1L));
        verify(customerFacade, times(1)).edit(customerDTO, 1L);
    }

    @Test
    @DisplayName("Should delete customer successfully")
    void shouldDeleteCustomerSuccessfully() throws CustomerNotFoundException {
        // Arrange
        when(customerFacade.delete(1L)).thenReturn(1);

        // Act
        int deletedCount = customerService.delete(1L);

        // Assert
        assertEquals(1, deletedCount, "Deleted count should be 1");
        verify(customerFacade, times(1)).delete(1L);
    }

    @Test
    @DisplayName("Should throw exception when deleting a non-existent customer")
    void shouldThrowException_whenDeletingNonExistentCustomer() throws CustomerNotFoundException {
        // Arrange
        when(customerFacade.delete(1L)).thenThrow(new CustomerNotFoundException("Customer not found"));

        // Act & Assert
        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(1L));
        verify(customerFacade, times(1)).delete(1L);
    }

    @Test
    @DisplayName("Should throw CustomerException when database fails during deletion")
    void shouldThrowException_whenDatabaseFailsDeletingCustomer() throws CustomerNotFoundException {
        when(customerFacade.delete(1L)).thenThrow(new RuntimeException("Database error"));

        assertThrows(CustomerException.class, () -> customerService.delete(1L));
    }

    @Test
    @DisplayName("save_ShouldThrowCustomerException_WhenFacadeThrowsException")
    void save_ShouldThrowCustomerException_WhenFacadeThrowsException() {
        when(customerFacade.save(any(CustomerDTO.class))).thenThrow(new RuntimeException("Database error"));

        assertThrows(CustomerException.class, () -> customerService.save(customerDTO));
        verify(customerFacade, times(1)).save(any(CustomerDTO.class));
    }

    @Test
    @DisplayName("delete_ShouldReturnRowCount_WhenSuccessful")
    void delete_ShouldReturnRowCount_WhenSuccessful() throws CustomerNotFoundException {
        // Arrange
        when(customerFacade.delete(1L)).thenReturn(1);

        // Act
        int deletedCount = customerService.delete(1L);

        // Assert
        assertEquals(1, deletedCount, "Deleted count should be 1");
        verify(customerFacade, times(1)).delete(1L);
    }

    @Test
    @DisplayName("delete_ShouldThrowCustomerNotFoundException_WhenCustomerDoesNotExist")
    void delete_ShouldThrowCustomerNotFoundException_WhenCustomerDoesNotExist() throws CustomerNotFoundException {
        when(customerFacade.delete(1L)).thenThrow(new CustomerNotFoundException("Customer not found"));

        assertThrows(CustomerNotFoundException.class, () -> customerService.delete(1L));
        verify(customerFacade, times(1)).delete(1L);
    }
    @Test
    @DisplayName("Should throw CustomerException when saving null customer")
    void shouldThrowException_whenSavingNullCustomer() {
        // Act & Assert
        assertThrows(CustomerException.class, () -> customerService.save(null));
    }

    @Test
    @DisplayName("Should throw CustomerException when editing with null customer")
    void shouldThrowException_whenEditingWithNullCustomer() {
        assertThrows(CustomerException.class, () -> customerService.edit(null, 1L));
    }

    @Test
    @DisplayName("Should throw CustomerException when editing with invalid ID")
    void shouldThrowException_whenEditingWithInvalidId() {
        assertThrows(CustomerException.class, () -> customerService.edit(customerDTO, null));
        assertThrows(CustomerException.class, () -> customerService.edit(customerDTO, -1L));
    }

    @Test
    @DisplayName("Should throw CustomerException when deleting with invalid ID")
    void shouldThrowException_whenDeletingWithInvalidId() {
        assertThrows(CustomerException.class, () -> customerService.delete(null));
        assertThrows(CustomerException.class, () -> customerService.delete(-1L));
    }

    @Test
    @DisplayName("Should throw CustomerException when fetching customer and database is down")
    void shouldThrowException_whenDatabaseFailsFetchingCustomer() throws CustomerNotFoundException {
        when(customerFacade.fetchCustomerById(1L)).thenThrow(new RuntimeException("Database error"));

        assertThrows(CustomerException.class, () -> customerService.getCustomerById(1L));
    }
}
