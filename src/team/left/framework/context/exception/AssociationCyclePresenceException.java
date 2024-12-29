package team.left.framework.context.exception;

public class AssociationCyclePresenceException extends BeanLoaderException {

    public AssociationCyclePresenceException(String message) {
        super(message);
    }
}
