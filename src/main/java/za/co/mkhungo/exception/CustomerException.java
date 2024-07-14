package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class CustomerException  extends Exception implements Serializable {
    public CustomerException(String message) {
        super( message);
    }
    public CustomerException() {super();}
    public CustomerException(String message, Throwable cause) {super(message,cause);}
}
