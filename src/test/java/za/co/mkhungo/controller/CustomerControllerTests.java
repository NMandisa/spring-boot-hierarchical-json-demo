package za.co.mkhungo.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.request.CustomerValueObject;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.service.CustomerService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Noxolo.Mkhungo
 */

class CustomerControllerTests {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomers() {
        CustomerResponse mockResponse = new CustomerResponse();
        when(customerService.getAllCustomers()).thenReturn(mockResponse);

        ResponseEntity<CustomerResponse> response = customerController.getCustomers();

        assertEquals(ResponseEntity.ok(mockResponse), response);
        verify(customerService).getAllCustomers();
    }

    @Test
    void testGetCustomer() throws CustomerNotFoundException {
        CustomerResponse mockResponse = new CustomerResponse();
        when(customerService.getCustomerById(anyLong())).thenReturn(mockResponse);

        ResponseEntity<CustomerResponse> response = customerController.getCustomer(1L);

        assertEquals(ResponseEntity.ok(mockResponse), response);
        verify(customerService).getCustomerById(1L);
    }

    @Test
    void testSaveCustomer() {
        CustomerValueObject customerValueObject = CustomerValueObject.builder().firstName("Noxolo").surname("Mkhungo").build();
        CustomerResponse mockResponse = CustomerResponse.builder().build();
        when(customerService.save(any(CustomerDTO.class))).thenReturn(mockResponse);

        ResponseEntity<CustomerResponse> response = customerController.saveCustomer(customerValueObject);

        assertEquals(ResponseEntity.accepted().body(mockResponse), response);
        verify(customerService).save(any(CustomerDTO.class));
    }

    @Test
    void testEditCustomer() throws CustomerException, CustomerNotFoundException {
        CustomerValueObject customerValueObject = CustomerValueObject.builder().firstName("Noxolo").surname("Mkhungo").build();

        CustomerResponse mockResponse = new CustomerResponse();
        when(customerService.edit(any(CustomerDTO.class), anyLong())).thenReturn(mockResponse);

        ResponseEntity<CustomerResponse> response = customerController.editCustomer(customerValueObject, 1L);

        assertEquals(ResponseEntity.accepted().body(mockResponse), response);
        verify(customerService).edit(any(CustomerDTO.class), eq(1L));
    }


}
