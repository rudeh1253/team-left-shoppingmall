package team.left.framework.web.view;

public interface ViewResolver {
    
    boolean isRedirect(String viewName);

    String resolve(String viewName);
}
