package hr.java.vjezbe.iznimke;

public class NemoguceOdreditiGrupuOsiguranjaException extends Exception{
    private static final long serialVersionUID = -8630254162361704171L;

    public NemoguceOdreditiGrupuOsiguranjaException() {
    }

    public NemoguceOdreditiGrupuOsiguranjaException(String message) {
        super(message);
    }

    public NemoguceOdreditiGrupuOsiguranjaException(String message, Throwable cause) {
        super(message, cause);
    }

    public NemoguceOdreditiGrupuOsiguranjaException(Throwable cause) {
        super(cause);
    }
}
