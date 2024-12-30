package team.left.shoppingmall.global.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import team.left.framework.context.annotation.Bean;
import team.left.framework.context.annotation.ComponentScan;
import team.left.framework.context.annotation.Configuration;
import team.left.framework.web.adapter.RequestMappingActionAdapter;
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
        return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
    }
    
    @Bean
    public RequestMappingActionAdapter requestMappingActionAdapter() {
        return new RequestMappingActionAdapter();
    }
    
    @Bean
    public DataSource dataSource() throws NamingException {
        Context context = new InitialContext();
        return (DataSource) context.lookup("java:comp/env/jdbc/Oracle");
    }
}
