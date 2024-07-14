package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class OrderException extends Exception implements Serializable {
    public OrderException(String message) {
        super( message);
    }
    public OrderException() {super();}
    public OrderException(String message, Throwable cause) {super(message,cause);}
}
