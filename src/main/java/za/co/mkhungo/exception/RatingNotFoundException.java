package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class RatingNotFoundException extends Exception implements Serializable {
    public RatingNotFoundException(String message) {
        super( message);
    }
    public RatingNotFoundException() {super();}
    public RatingNotFoundException(String message, Throwable cause) {super(message,cause);}
}
