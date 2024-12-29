package team.left.shoppingmall.global.web;

import team.left.framework.context.annotation.Bean;
import team.left.framework.context.annotation.ComponentScan;
import team.left.framework.context.annotation.Configuration;
import team.left.framework.web.resolver.handler.RequestMappingActionMapping;
import team.left.framework.web.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackage = "team.left.shoppingmall")
public class BaseConfig {
    
    @Bean
    public RequestMappingActionMapping requestMappingActionMapping() {
        return new RequestMappingActionMapping();
    }
    
    @Bean
    public InternalResourceViewResolver viewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/views", ".jsp");
    }
}
