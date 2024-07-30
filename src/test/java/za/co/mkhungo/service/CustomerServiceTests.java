package za.co.mkhungo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.exception.CustomerException;
import za.co.mkhungo.exception.CustomerNotFoundException;
import za.co.mkhungo.facade.CustomerFacade;
import za.co.mkhungo.helper.PopulateResponseHelper;
import za.co.mkhungo.response.CustomerResponse;
import za.co.mkhungo.service.impl.DefaultCustomerService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Noxolo.Mkhungo
 */
class CustomerServiceTests {

    @Mock
    private CustomerFacade customerFacade;

    @Mock
    private PopulateResponseHelper populateResponseHelper;

    @InjectMocks
    private DefaultCustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("test Get All Customers ")
    void testGetAllCustomers() {
        // Setup
        CustomerResponse mockResponse = new CustomerResponse();
        when(customerFacade.fetchAllCustomers()).thenReturn(List.of(new CustomerDTO()));
        when(populateResponseHelper.populateCustomerResponse(Collections.singletonList(any()))).thenReturn(mockResponse);

        // Execute
        CustomerResponse response = customerService.getAllCustomers();

        // Verify
        assertNotNull(response);
        assertEquals(mockResponse, response);
        verify(customerFacade).fetchAllCustomers();
        verify(populateResponseHelper).populateCustomerResponse(Collections.singletonList(any()));
    }

    @Test
    void testGetCustomerById() throws CustomerNotFoundException {
        CustomerDTO mockCustomer = new CustomerDTO();
        mockCustomer.setId(1L);
        mockCustomer.setFirstName("Mandisa");
        mockCustomer.setSurname("Mkhungo");

        CustomerResponse mockResponse = new CustomerResponse();
        when(customerFacade.fetchCustomerById(anyLong())).thenReturn(mockCustomer);
        when(populateResponseHelper.populateCustomerResponse(mockCustomer)).thenReturn(mockResponse);

        CustomerResponse response = customerService.getCustomerById(1L);

        assertNotNull(response);
        assertEquals(mockResponse, response);
    }

    @Test
    void testEdit() throws CustomerException, CustomerNotFoundException {
        // Setup
        CustomerDTO mockCustomerDTO = CustomerDTO.builder()
                .id(1L)
                .firstName("Mandisa")
                .surname("Mkhungo")
                .build();
        CustomerResponse mockResponse = new CustomerResponse();

        when(customerFacade.edit(any(CustomerDTO.class), anyLong())).thenReturn(mockCustomerDTO);
        when(populateResponseHelper.populateCustomerResponse(any(CustomerDTO.class))).thenReturn(mockResponse);

        // Execute
        CustomerDTO customerToEdit = CustomerDTO.builder()
                .firstName("Sibongile")
                .surname("Zulu")
                .build();

        CustomerResponse response = customerService.edit(customerToEdit, 1L);

        // Verify
        assertNotNull(response, "The response should not be null");
        assertEquals(mockResponse, response);
    }

    @Test
    void testDelete() throws CustomerNotFoundException {
        // Setup
        when(customerFacade.delete(anyLong())).thenReturn(1);

        // Execute
        int rowsAffected = customerService.delete(1L);

        // Verify
        assertEquals(1, rowsAffected);
        verify(customerFacade).delete(1L);
    }


}
