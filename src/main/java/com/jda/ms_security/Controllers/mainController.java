package com.jda.ms_security.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class mainController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
