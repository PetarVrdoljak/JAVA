package hr.java.vjezbe.iznimke;

public class OzbiljnaGreska extends Exception{
    public OzbiljnaGreska() {
    }

    public OzbiljnaGreska(String message) {
        super(message);
    }

    public OzbiljnaGreska(String message, Throwable cause) {
        super(message, cause);
    }

    public OzbiljnaGreska(Throwable cause) {
        super(cause);
    }

    public OzbiljnaGreska(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
