package za.co.mkhungo.exception;

import java.io.Serializable;

/**
 * @author Noxolo.Mkhungo
 */
public class ReviewException extends Exception implements Serializable {
    public ReviewException(String message) {
        super( message);
    }
    public ReviewException() {super();}
    public ReviewException(String message, Throwable cause) {super(message,cause);}
}
