package team.left.shoppingmall.sample;

import team.left.framework.web.action.Handler;
import team.left.framework.web.action.RequestMethod;
import team.left.framework.web.constant.CommonConstants;
import team.left.framework.web.model.Model;
import team.left.framework.web.parameter.RequestParam;
import team.left.framework.web.parameter.RequestParamContainer;
import team.left.framework.web.prototype.Action;

@Action
public class SampleAction2 {

    @Handler(path = "/sample2.do", method = RequestMethod.GET, command = "hi")
    public String sample(@RequestParam("say") String say, Model model, @RequestParamContainer SampleDto sampleDto) {
        System.out.println(sampleDto);
        model.addAttribute("talk", "I said: " + say);
        model.addAttribute("what", "what I did");
        model.addAttribute("dto", sampleDto);
        return CommonConstants.REDIRECT_PREFIX + "/hello.do?command=hi";
    }
}
