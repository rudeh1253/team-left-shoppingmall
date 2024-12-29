package team.left.framework.context.testbeans;

import team.left.framework.context.annotation.Bean;
import team.left.framework.context.annotation.ComponentScan;
import team.left.framework.context.annotation.Configuration;
import team.left.framework.context.testbeans.a.A;

@Configuration
@ComponentScan(basePackage = "team.left.framework.context.testbeans")
public class BeanConfig {

    @Bean
    public A a() {
        return new A();
    }
}
