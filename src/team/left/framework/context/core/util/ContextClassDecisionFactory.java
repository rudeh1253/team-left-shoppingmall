package team.left.framework.context.core.util;

public class ContextClassDecisionFactory {
    private static final ContextClassDecision singleton = new DefaultContextClassDecision();

    public static ContextClassDecision getInstance() {
        return singleton;
    }
}
