package com.example.task.controllers;

import com.example.task.algorithm.HtmlReader;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.IOException;

@RestController
public class SecurityController {
    @Autowired
    DataSource dataSource;

    HtmlReader htmlReader = new HtmlReader();

    @GetMapping("/registration")
    public String registration() {
        return htmlReader.readHtml("src/main/resources/templates/registration.html");
    }
    @PostMapping("/registration")
    public void addUser(HttpServletResponse response, @RequestParam String username,
                        @RequestParam String password) throws IOException {
        
        response.sendRedirect("/login");
    }
}
