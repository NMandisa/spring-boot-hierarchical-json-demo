package za.co.mkhungo.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.response.node.CustomerTree;
import za.co.mkhungo.response.node.OrderTree;
import za.co.mkhungo.response.node.sub.CustomerSubTree;
import za.co.mkhungo.strategy.ControllerLinkProvider;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * @author Noxolo.Mkhungo
 */
@ExtendWith(MockitoExtension.class)
class CustomerTreePopulatorTest {

    @Mock
    private OrderTreePopulator orderTreePopulator;

    @Mock
    private ControllerLinkProvider linkProvider;

    @InjectMocks
    private CustomerTreePopulator customerTreePopulator;

    private CustomerDTO customerDTO;

    @BeforeEach
    void setUp() {
        customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName("John");
        customerDTO.setSurname("Doe");
        customerDTO.setOrders(Collections.emptyList());
    }

    @Test
    @DisplayName("Should correctly map CustomerDTO to CustomerSubTree")
    void shouldMapToSubTree() {
        // Arrange
        when(linkProvider.getSelfLink(1L)).thenReturn(Link.of("/customers/1"));
        when(orderTreePopulator.populateOrderTree(Collections.emptyList())).thenReturn(new OrderTree());

        // Act
        CustomerSubTree result = customerTreePopulator.mapToSubTree(customerDTO);

        // Assert
        assertAll("CustomerSubTree Properties",
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(1L, result.getId(), "ID should be correctly mapped"),
                () -> assertEquals("John", result.getFirstName(), "First name should match"),
                () -> assertEquals("Doe", result.getSurname(), "Surname should match"),
                () -> assertNotNull(result.getOrders(), "Orders should not be null"),
                () -> assertTrue(result.getLinks().hasLink("self"), "Self link should be present")
        );

        verify(linkProvider).getSelfLink(1L);
        verify(orderTreePopulator).populateOrderTree(Collections.emptyList());
    }

    @Test
    @DisplayName("Should correctly populate CustomerTree with CustomerDTO list")
    void shouldPopulateCustomerTree() {
        // Arrange
        when(linkProvider.getSelfLink(1L)).thenReturn(Link.of("/customers/1"));
        when(orderTreePopulator.populateOrderTree(Collections.emptyList())).thenReturn(new OrderTree());

        // Act
        CustomerTree result = customerTreePopulator.populateCustomerTree(List.of(customerDTO));

        // Assert
        assertAll("CustomerTree Properties",
                () -> assertNotNull(result, "Result should not be null"),
                () -> assertEquals(1, result.getCustomer().size(), "Should contain one customer"),
                () -> assertEquals("John", result.getCustomer().get(0).getFirstName(), "First name should match"),
                () -> assertEquals("Doe", result.getCustomer().get(0).getSurname(), "Surname should match")
        );

        verify(linkProvider).getSelfLink(1L);
        verify(orderTreePopulator).populateOrderTree(Collections.emptyList());
    }

    @Test
    @DisplayName("Should return empty CustomerTree when input list is empty")
    void shouldReturnEmptyCustomerTreeForEmptyList() {
        // Act
        CustomerTree result = customerTreePopulator.populateCustomerTree(Collections.emptyList());

        // Assert
        assertNotNull(result, "Result should not be null");
        assertTrue(result.getCustomer().isEmpty(), "Customer list should be empty");
    }

    @Test
    @DisplayName("Should handle null CustomerDTO without throwing an exception")
    void shouldHandleNullCustomerDTO() {
        // Act
        CustomerTree result = customerTreePopulator.populateCustomerTree(Collections.singletonList(null));

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(0, result.getCustomer().size(), "Customer list should be empty");

        verifyNoInteractions(linkProvider, orderTreePopulator);
    }

    @Test
    @DisplayName("Should handle null OrderTree gracefully")
    void shouldHandleNullOrderTree() {
        // Arrange
        when(linkProvider.getSelfLink(1L)).thenReturn(Link.of("/customers/1"));
        when(orderTreePopulator.populateOrderTree(Collections.emptyList())).thenReturn(null);

        // Act
        CustomerSubTree result = customerTreePopulator.mapToSubTree(customerDTO);

        // Assert
        assertNotNull(result, "Result should not be null");
        assertNull(result.getOrders(), "Orders should be null when OrderTree is null");
    }
}
