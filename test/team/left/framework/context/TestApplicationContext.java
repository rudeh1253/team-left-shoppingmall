package team.left.framework.context;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import team.left.framework.context.testbeans.BeanConfig;
import team.left.framework.context.testbeans.Printable;
import team.left.framework.context.testbeans.a.A;
import team.left.framework.context.testbeans.a.deeper.SomeAnnotation;
import team.left.framework.context.testbeans.b.B;
import test.util.TestRun;

/**
 * 테스트는 print한 거 눈으로만 확인
 */
public class TestApplicationContext {

    public static void main(String[] args) {
        TestApplicationContext runner = new TestApplicationContext();
        Method[] methods = Arrays.stream(TestApplicationContext.class.getDeclaredMethods())
                .filter((m) -> m.isAnnotationPresent(TestRun.class))
                .toArray(Method[]::new);
        
        try {
            for (Method method : methods) {
                method.invoke(runner);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @TestRun
    private void testThing() {
        ApplicationContext container = AnnotationBasedApplicationContextFactory.getApplicationContext(BeanConfig.class);
        
        A a = container.getBean(A.class);
        List<B> b = container.getBeansOfType(B.class);
        List<Printable> printable = container.getBeansOfType(Printable.class); 
        Map<String, Object> fromAnnotation = container.getBeansWithAnnotation(SomeAnnotation.class);
        a.hello();
        System.out.println("1---------");
        for (B i : b) {
            i.print();
        }
        System.out.println("2---------");
        for (Printable p : printable) {
            if (p.getClass() == B.class) {
                System.out.println(p.getClass());
            }
            p.print();
        }
        System.out.println("3---------");
        System.out.println(fromAnnotation);
    }
}
