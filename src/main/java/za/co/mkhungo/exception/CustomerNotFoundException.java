package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class CustomerNotFoundException extends Exception implements Serializable {
    public CustomerNotFoundException(String message) {
        super( message);
    }
    public CustomerNotFoundException() {super();}
    public CustomerNotFoundException(String message, Throwable cause) {super(message,cause);}
}
