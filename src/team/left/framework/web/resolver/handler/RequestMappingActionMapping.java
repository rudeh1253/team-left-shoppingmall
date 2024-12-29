package team.left.framework.web.resolver.handler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import team.left.framework.context.ApplicationContext;
import team.left.framework.web.action.Command;
import team.left.framework.web.action.RequestMethod;
import team.left.framework.web.prototype.Action;

public class RequestMappingActionMapping {
    private Map<MappingInfo, HandlerSet> handlerByMapping;

    public void init(ApplicationContext context) {
        Map<String, Object> beanByName = context.getBeansWithAnnotation(Action.class);

        Map<MappingInfo, HandlerSet> handlersByMappingCache = new HashMap<>();
        for (Object handler : beanByName.values()) {
            Method[] handlerMethods = Arrays.stream(handler.getClass().getDeclaredMethods())
                    .filter((e) -> e.isAnnotationPresent(Command.class)).toArray(Method[]::new);
            for (Method handlerMethod : handlerMethods) {
                Command command = handlerMethod.getAnnotation(Command.class);
                String path = command.path();
                RequestMethod method = command.method();
                String cmd = command.cmd();

                handlersByMappingCache.put(new MappingInfo(path, method, cmd), new HandlerSet(handler, handlerMethod));
            }
        }
        
        this.handlerByMapping = Collections.unmodifiableMap(handlersByMappingCache);
        
        System.out.println(this.handlerByMapping);
    }

    public HandlerSet getHandler(String path, RequestMethod method, String command) {
        return getHandler(new MappingInfo(path, method, command));
    }

    public HandlerSet getHandler(MappingInfo mappingInfo) {
        System.out.println("mappingInfo=" + mappingInfo);
        return this.handlerByMapping.get(mappingInfo);
    }
}
