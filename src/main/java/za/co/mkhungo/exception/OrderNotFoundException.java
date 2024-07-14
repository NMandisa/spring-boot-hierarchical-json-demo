package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class OrderNotFoundException extends Exception implements Serializable {
    public OrderNotFoundException(String message) {
        super( message);
    }
    public OrderNotFoundException() {super();}
    public OrderNotFoundException(String message, Throwable cause) {super(message,cause);}
}
