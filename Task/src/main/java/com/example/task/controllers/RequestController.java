package com.example.task.controllers;

import ch.qos.logback.core.model.Model;
import com.example.task.algorithm.HtmlReader;
import com.example.task.entyties.StopWord;
import com.example.task.repository.RequestRepository;
import com.example.task.repository.StopWordsRepository;
import com.example.task.services.RequestService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestController {
    private final RequestService requestService;

    private final StopWordsRepository stopWordsRepository;
    private final RequestRepository requestRepository;
    private final HtmlReader htmlReader = new HtmlReader();

    public RequestController(RequestService requestService, StopWordsRepository stopWordsRepository, RequestRepository requestRepository) {
        this.requestService = requestService;
        this.stopWordsRepository = stopWordsRepository;
        this.requestRepository = requestRepository;
    }

    @GetMapping(path = "/res")
    public List<String> getResult() {

        return List.of(requestRepository.findAll().toString());
    }

    @GetMapping(path = "/main")
    public String getDefault() {
        if(stopWordsRepository.count() == 0) addStopWordsToBD();
        return htmlReader.readHtml("src/main/resources/templates/index.html");
    }

    @PostMapping(path = "/main")
    public String add (@RequestParam String str1, @RequestParam String str2){
        requestService.add(str1,str2);
        return htmlReader.readHtml("src/main/resources/templates/index.html");
    }
    private void addStopWordsToBD(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/static/StopWords"));
            String line = br.readLine();
            while (line != null) {
                stopWordsRepository.save(new StopWord(line));
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
        } finally {

        }
    }



}
