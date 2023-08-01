package com.example.task.controllers;

import com.example.task.algorithm.HtmlReader;
import com.example.task.entyties.StopWord;
import com.example.task.repository.RequestRepository;
import com.example.task.repository.StopWordsRepository;
import com.example.task.repository.UserRepository;
import com.example.task.services.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class MainController {
    @Autowired
    UserRepository userRepository;
    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final HtmlReader htmlReader = new HtmlReader();

    public MainController(RequestService requestService, RequestRepository requestRepository) {
        this.requestService = requestService;
        this.requestRepository = requestRepository;
    }

    @GetMapping(path = "/res")
    public ModelAndView getResult(Authentication auth) {
        var user =  userRepository.findUserByUsername(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("result");
        var lst = List.of(requestRepository.findAllByUserId(user.getId()).toArray());
        modelAndView.addObject("lst",lst);
        return modelAndView;
    }

    @GetMapping(path = "/main")
    public String getDefault() {
        requestService.checkBd();
        return htmlReader.readHtml("src/main/resources/templates/index.html");
    }

    @PostMapping(path = "/main")
    public String add (Authentication auth, @RequestParam String str1, @RequestParam String str2){

       try {
           requestService.add(auth, str1,str2);
       }catch (StringIndexOutOfBoundsException e){
           return htmlReader.readHtml("src/main/resources/templates/excPage.html");
       }
        return htmlReader.readHtml("src/main/resources/templates/index.html");
    }
}
