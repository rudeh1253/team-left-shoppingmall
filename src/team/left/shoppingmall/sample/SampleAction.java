package team.left.shoppingmall.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.action.Handler;
import team.left.framework.web.action.RequestMethod;
import team.left.framework.web.prototype.Action;

@Action
public class SampleAction {

    @Handler(path = "/hello.do", method = RequestMethod.GET, command = "hi")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("hello", "Hi");
        return "/hello";
    }
}
