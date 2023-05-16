package hr.java.vjezbe.iznimke;

public class GreskaUProgramu extends RuntimeException {

    public GreskaUProgramu() {
    }

    public GreskaUProgramu(String message) {
        super(message);
    }

    public GreskaUProgramu(String message, Throwable cause) {
        super(message, cause);
    }

    public GreskaUProgramu(Throwable cause) {
        super(cause);
    }

    public GreskaUProgramu(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
