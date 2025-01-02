package team.left.framework.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
    
    default void init(ServletConfig servletConfig) {
    }

    String handleCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
