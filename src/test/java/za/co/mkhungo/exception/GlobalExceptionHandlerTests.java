package za.co.mkhungo.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.co.mkhungo.exception.handler.GlobalExceptionHandler;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Noxolo.Mkhungo
 */
@DisplayName("Global Exception Handler Tests")
@Slf4j
class GlobalExceptionHandlerTests {

        @InjectMocks
        private GlobalExceptionHandler globalExceptionHandler;

        @BeforeEach
        void setUp() {
        MockitoAnnotations.openMocks(this);
        }

        @Test
        @DisplayName("Test Handle Exception")
        void testHandleException() {
        Exception exception = new Exception("Test Exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleException(exception);
        assertNotNull(responseEntity);
        log.info("ResponseEntity : {}",responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Test Exception", ((Exception) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleCustomerException() {
        CustomerException exception = new CustomerException("Customer Exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleCustomerException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Customer Exception", ((CustomerException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleCustomerNotFoundException() {
        CustomerNotFoundException exception = new CustomerNotFoundException("Customer Not Found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleCustomerNotFoundException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Customer Not Found", ((CustomerNotFoundException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleOrderException() {
        OrderException exception = new OrderException("Order Exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleOrderException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Order Exception", ((OrderException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleOrderNotFoundException() {
        OrderNotFoundException exception = new OrderNotFoundException("Order Not Found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleOrderNotFoundException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Order Not Found", ((OrderNotFoundException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleProductException() {
        ProductException exception = new ProductException("Product Exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleProductException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Product Exception", ((ProductException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleProductNotFoundException() {
        ProductNotFoundException exception = new ProductNotFoundException("Product Not Found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleProductNotFoundException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Product Not Found", ((ProductNotFoundException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleRatingException() {
        RatingException exception = new RatingException("Rating Exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleRatingException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Rating Exception", ((RatingException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleRatingNotFoundException() {
        RatingNotFoundException exception = new RatingNotFoundException("Rating Not Found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleRatingNotFoundException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Rating Not Found", ((RatingNotFoundException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
    }

        @Test
        void testHandleReviewException() {
        ReviewException exception = new ReviewException("Review Exception");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleReviewException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Review Exception", ((ReviewException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }

        @Test
        void testHandleReviewNotFoundException() {
        ReviewNotFoundException exception = new ReviewNotFoundException("Review Not Found");
        ResponseEntity<?> responseEntity = globalExceptionHandler.handleReviewNotFoundException(exception);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Review Not Found", ((ReviewNotFoundException) Objects.requireNonNull(responseEntity.getBody())).getMessage());
        }
}
