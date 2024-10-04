package io.github.faizansaghir.webAppWithSpringAndSpringBoot.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {

    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello(){
        return "Hello! How are you doing?";
    }
    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml(){
        return "<html><head><title>My First HTML Page</title></head><body>Hello! Welcome to my HTML page</body></html>";
    }

}
