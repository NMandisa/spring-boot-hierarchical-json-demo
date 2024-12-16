package za.co.mkhungo.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import za.co.mkhungo.exception.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Noxolo.Mkhungo
 */
@RestControllerAdvice(annotations ={RestController.class})
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public final ResponseEntity<?> handleException(Exception ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public final ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);//403
    }

    @ExceptionHandler(value = {CustomerException.class})
    public final ResponseEntity<?> handleCustomerException(CustomerException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }
    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public final ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.NOT_FOUND);//404
    }
    @ExceptionHandler(value = {OrderException.class})
    public final ResponseEntity<?> handleOrderException(OrderException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }
    @ExceptionHandler(value = {OrderNotFoundException.class})
    public final ResponseEntity<?> handleOrderNotFoundException(OrderNotFoundException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.NOT_FOUND);//404
    }
    @ExceptionHandler(value = {ProductException.class})
    public final ResponseEntity<?> handleProductException(ProductException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }
    @ExceptionHandler(value = {ProductNotFoundException.class})
    public final ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.NOT_FOUND);//404
    }

    @ExceptionHandler(value = {RatingException.class})
    public final ResponseEntity<?> handleRatingException(RatingException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }
    @ExceptionHandler(value = {RatingNotFoundException.class})
    public final ResponseEntity<?> handleRatingNotFoundException(Exception ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.NOT_FOUND);//404
    }
    @ExceptionHandler(value = {ReviewException.class})
    public final ResponseEntity<?> handleReviewException(ReviewException ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.INTERNAL_SERVER_ERROR);//500
    }

    @ExceptionHandler(value = {ReviewNotFoundException.class})
    public final ResponseEntity<?> handleReviewNotFoundException(Exception ex) {
        return new ResponseEntity<>(ex,null, HttpStatus.NOT_FOUND);//404
    }
}
