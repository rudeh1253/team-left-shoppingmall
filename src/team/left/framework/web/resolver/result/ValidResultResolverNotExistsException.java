package team.left.framework.web.resolver.result;

public class ValidResultResolverNotExistsException extends RuntimeException {
    
    public ValidResultResolverNotExistsException(String message) {
        super(message);
    }
    
    public ValidResultResolverNotExistsException(Class<?> clazz) {
        super("유효한 ResultResolver가 없습니다: " + clazz);
    }
}
