package team.left.framework.context.exception;

public class NoPublicConstructorException extends BeanLoaderException {

    public NoPublicConstructorException(String message) {
        super(message);
    }
}
