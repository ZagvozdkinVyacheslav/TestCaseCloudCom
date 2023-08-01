package com.example.task.controllers;

import com.example.task.algorithm.HtmlReader;
import com.example.task.entyties.Authority;
import com.example.task.entyties.User;
import com.example.task.repository.AuthorityRepository;
import com.example.task.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.io.IOException;

@RestController
public class SecurityController {
    @Autowired
    DataSource dataSource;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    HtmlReader htmlReader = new HtmlReader();


    @GetMapping("/registration")
    public ModelAndView getRegistration(String username, String password, String confirmPassword) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        if (username == null && password == null && confirmPassword == null) {
            return modelAndView;
        }
        if (username.length() < 4 && username.length() > 30) {
            modelAndView.addObject("message", "Username must be more than 4 and less than 30 characters!");
            return modelAndView;
        }
        if (!password.equals(confirmPassword)) {
            modelAndView.addObject("username", username);
            modelAndView.addObject("message", "Passwords don`t match!");
            return modelAndView;
        }
        if (password.length() < 6 && password.length() > 30) {
            modelAndView.addObject("username", username);
            modelAndView.addObject("message", "Password must be more than 6 and less than 30 characters!");
            return modelAndView;
        }
        if (userRepository.findUserByUsername(username) != null) {
            modelAndView.addObject("message", "User with this username already exists!");
            return modelAndView;
        }

        User user = new User(username, password, true);
        userRepository.save(user);
        Authority authority = new Authority(username, Authority.Role.ROLE_USER);
        authorityRepository.save(authority);

        modelAndView.addObject("message", "User %s successfully saved!".formatted(username));
        return modelAndView;
    }
}
