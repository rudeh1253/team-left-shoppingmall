package team.left.framework.context.testbeans.c;

import team.left.framework.context.annotation.Component;
import team.left.framework.context.testbeans.b.B;

@Component
public class C extends B {

    @Override
    public void print() {
        System.out.println("print: C");
    }
}
