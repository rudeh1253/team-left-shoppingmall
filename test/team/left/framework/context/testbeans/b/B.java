package team.left.framework.context.testbeans.b;

import team.left.framework.context.annotation.Component;
import team.left.framework.context.testbeans.Printable;

@Component
public class B implements Printable {

    @Override
    public void print() {
        System.out.println("print: B");
    }
}
