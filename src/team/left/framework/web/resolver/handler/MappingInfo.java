package team.left.framework.web.resolver.handler;

import java.util.Objects;

import team.left.framework.web.action.RequestMethod;

public class MappingInfo {
    private final String path;
    private final RequestMethod method;
    private final String command;
    
    public MappingInfo(String path, RequestMethod method, String command) {
        this.path = path;
        this.method = method;
        this.command = command;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MappingInfo) {
            MappingInfo target = (MappingInfo) obj;
            
            return this.path.contentEquals(target.path)
                    && this.method == target.method
                    && this.command.equals(target.command);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.path, this.method, this.command);
    }

    @Override
    public String toString() {
        return "MappingInfo [path=" + path + ", method=" + method + ", command=" + command + "]";
    }
}
