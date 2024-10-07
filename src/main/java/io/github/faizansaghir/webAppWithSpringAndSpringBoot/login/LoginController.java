package io.github.faizansaghir.webAppWithSpringAndSpringBoot.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String renderLoginPage(@RequestParam String name, ModelMap model) {
        model.addAttribute("name", name);
        return "login";
    }

}
