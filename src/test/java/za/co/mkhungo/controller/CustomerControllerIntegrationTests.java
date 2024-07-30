package za.co.mkhungo.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.mkhungo.dto.CustomerDTO;
import za.co.mkhungo.request.CustomerValueObject;
import za.co.mkhungo.response.CustomerResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Noxolo.Mkhungo
 */
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIntegrationTests {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetCustomers() {
        ResponseEntity<CustomerResponse> response = restTemplate.getForEntity(createURLWithPort("/customers/"), CustomerResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    //@Test
    void testSaveCustomer() {
        CustomerValueObject customerValueObject = CustomerValueObject.builder().firstName("Mandisa").surname("Mkhungo").build();
        ResponseEntity<CustomerResponse> response = restTemplate.postForEntity(createURLWithPort("/customers/"),
                CustomerDTO.builder().firstName(customerValueObject.getFirstName()).surname(customerValueObject.getSurname()).build(),
                CustomerResponse.class);

        if (response.getStatusCode() != HttpStatus.ACCEPTED) {
            System.out.println("Error occurred: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());
        }

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode(), "Expected 202 ACCEPTED");
        assertNotNull(response.getBody(), "The response should not be null");
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}