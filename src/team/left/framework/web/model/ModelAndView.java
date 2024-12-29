package team.left.framework.web.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private String viewName;
    private Map<String, Object> attributes;
    
    public ModelAndView() {
        this(null, new HashMap<>());
    }
    
    public ModelAndView(String viewName) {
        this(viewName, new HashMap<>());
    }
    
    public ModelAndView(Map<String, Object> attributes) {
        this(null, attributes);
    }
    
    public ModelAndView(String viewName, Map<String, Object> attributes) {
        this.viewName = viewName;
        this.attributes = attributes;
    }
    
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    public void addAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }
    
    public void addAllAttributes(Map<String, Object> attributes) {
        this.attributes.putAll(attributes);
    }
    
    public String getViewName() {
        return this.viewName;
    }
    
    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }
}
