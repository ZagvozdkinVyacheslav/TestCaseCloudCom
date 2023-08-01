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

    @GetMapping("/registration")
    public ModelAndView getRegistration(String username, String password, String confirmPassword) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        if (username == null && password == null && confirmPassword == null) {
            return modelAndView;
        }
        if (username.length() < 4 || username.length() > 30) {
            modelAndView.addObject("message", "Имя должно быть более 4 символов и менее 30");
            return modelAndView;
        }
        if (!password.equals(confirmPassword)) {
            modelAndView.addObject("username", username);
            modelAndView.addObject("message", "Пароли не совпадают");
            return modelAndView;
        }
        if (password.length() < 4 || password.length() > 20) {
            modelAndView.addObject("username", username);
            modelAndView.addObject("message", "Пароль должен содержать больше 4 и менее 20");
            return modelAndView;
        }
        if (userRepository.findUserByUsername(username) != null) {
            modelAndView.addObject("message", "Пользователь с таким именем уже существует");
            return modelAndView;
        }

        User user = new User(username, password, true);
        userRepository.save(user);
        Authority authority = new Authority(username, Authority.Role.ROLE_USER);
        authorityRepository.save(authority);

        modelAndView.addObject("message", "Пользователь %s зарегистрирован!".formatted(username));
        return modelAndView;
    }
}
