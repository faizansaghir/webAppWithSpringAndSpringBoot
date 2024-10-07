package io.github.faizansaghir.webAppWithSpringAndSpringBoot.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/login")
    public String renderLoginPage(@RequestParam String name, ModelMap model) {
        logger.debug("Request paramater name: {}", name);
        model.addAttribute("name", name);
        return "login";
    }

}
