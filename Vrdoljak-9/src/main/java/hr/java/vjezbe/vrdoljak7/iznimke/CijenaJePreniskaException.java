package hr.java.vjezbe.vrdoljak7.iznimke;

public class CijenaJePreniskaException extends RuntimeException{

    private static final long serialVersionUID = -3120061106107961263L;
    public CijenaJePreniskaException() {
    }

    public CijenaJePreniskaException(String message) {
        super(message);
    }

    public CijenaJePreniskaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CijenaJePreniskaException(Throwable cause) {
        super(cause);
    }

    public CijenaJePreniskaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
