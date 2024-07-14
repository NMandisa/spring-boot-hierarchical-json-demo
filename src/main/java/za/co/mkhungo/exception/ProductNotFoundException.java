package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class ProductNotFoundException extends Exception implements Serializable {
    public ProductNotFoundException(String message) {
        super( message);
    }
    public ProductNotFoundException() {super();}
    public ProductNotFoundException(String message, Throwable cause) {super(message,cause);}
}
