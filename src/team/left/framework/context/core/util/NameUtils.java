package team.left.framework.context.core.util;

public class NameUtils {
    
    public static String generateDefaultBeanName(Class<?> clazz) {
        return convertToCamelCase(clazz.getSimpleName());
    }
    
    public static String convertToCamelCase(String startWithUpperCase) {
        char[] charArr = startWithUpperCase.toCharArray();
        charArr[0] = Character.toLowerCase(charArr[0]);
        return String.valueOf(charArr);
    }
}
