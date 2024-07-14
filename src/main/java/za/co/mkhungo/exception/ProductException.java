package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class ProductException extends Exception implements Serializable {
    public ProductException(String message) {
        super( message);
    }
    public ProductException() {super();}
    public ProductException(String message, Throwable cause) {super(message,cause);}
}
