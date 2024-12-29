package team.left.shoppingmall.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import team.left.framework.web.action.Command;
import team.left.framework.web.action.RequestMethod;
import team.left.framework.web.prototype.Action;

@Action
public class SampleAction {

    @Command(path = "/hello.do", method = RequestMethod.GET, cmd = "hi")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("hello", "Hi");
        return "/hello";
    }
}
