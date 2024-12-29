package team.left.framework.context.exception;

public class BeanLoaderException extends RuntimeException {

    public BeanLoaderException(String message) {
        super(message);
    }

    public BeanLoaderException(String message, Throwable cause) {
        super (message, cause);
    }
}
