package team.left.framework.web.view;

import team.left.framework.web.constant.CommonConstants;

public class InternalResourceViewResolver implements ViewResolver {
    private final String prefix;
    private final String suffix;
    
    public InternalResourceViewResolver(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public boolean isRedirect(String viewName) {
        return viewName.startsWith(CommonConstants.REDIRECT_PREFIX);
    }

    @Override
    public String resolve(String viewName) {
        return this.prefix + viewName + this.suffix;
    }
}
