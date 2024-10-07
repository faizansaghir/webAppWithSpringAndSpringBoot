package io.github.faizansaghir.webAppWithSpringAndSpringBoot.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public boolean authenticate(String username, String password){
        boolean isValidUsername = username.equalsIgnoreCase("faizan");
        boolean isValidPassword = password.equalsIgnoreCase("pass");
        return isValidUsername && isValidPassword;
    }
}
