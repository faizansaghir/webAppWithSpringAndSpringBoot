package io.github.faizansaghir.webAppWithSpringAndSpringBoot.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/welcome")
    public String renderWelcomePage(@RequestParam String username, ModelMap model) {
        logger.debug("Request parameter name: {}", username);
        model.addAttribute("username", username);
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@RequestParam String username, @RequestParam String password, ModelMap model) {
        if(authenticationService.authenticate(username, password)){
            model.put("username", username);
            return "welcome";
        }
        else{
            model.put("errorMessage", "Invalid username/password");
            return "login";
        }
    }
}
