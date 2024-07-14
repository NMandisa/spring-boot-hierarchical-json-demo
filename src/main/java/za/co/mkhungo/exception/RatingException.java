package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class RatingException extends Exception implements Serializable {
    public RatingException(String message) {
        super( message);
    }
    public RatingException() {super();}
    public RatingException(String message, Throwable cause) {super(message,cause);}
}
