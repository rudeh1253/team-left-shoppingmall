package team.left.framework.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {

    String handleCommand(HttpServletRequest request, HttpServletResponse response, String method, String command) throws ServletException, IOException;
}
