package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class ReviewNotFoundException extends Exception implements Serializable {
    public ReviewNotFoundException(String message) {
        super( message);
    }
    public ReviewNotFoundException() {super();}
    public ReviewNotFoundException(String message, Throwable cause) {super(message,cause);}
}
