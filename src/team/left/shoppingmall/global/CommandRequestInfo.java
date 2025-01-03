package team.left.shoppingmall.global;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class CommandRequestInfo {
    private final String uri;
    private final String method;
    private final String command;
    
    public CommandRequestInfo(String uri, String method, String command) {
        this.uri = uri;
        this.method = method.trim().toUpperCase();
        this.command = command;
    }
    
    public static CommandRequestInfo from(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String requestUri = request.getRequestURI();
        
        if (contextPath != null && !contextPath.isEmpty()) {
            requestUri = requestUri.substring(contextPath.length());
        }
        
        String command = request.getParameter("command");
        String method = request.getMethod().trim().toUpperCase();
        
        return new CommandRequestInfo(requestUri, method, command);
    }
    
    public static CommandRequestInfo of(String[] strs) {
        if (strs.length != 3) {
            throw new IllegalArgumentException("배열의 길이가 3이어야 함: str=" + Arrays.toString(strs) + ", strs.length=" + strs.length);
        }
        return new CommandRequestInfo(strs[0], strs[1], strs[2]);
    }
}
