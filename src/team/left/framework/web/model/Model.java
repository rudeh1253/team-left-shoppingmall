package team.left.framework.web.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private Map<String, Object> attributes;
    
    public Model() {
        this(new HashMap<>());
    }
    
    public Model(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public void addAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }
    
    public void addAllAttributes(Map<String, Object> attributes) {
        this.attributes.putAll(attributes);
    }
    
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }
}
