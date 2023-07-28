package com.example.task.controllers;

import com.example.task.entyties.PostRequest;
import com.example.task.entyties.StopWord;
import com.example.task.repository.StopWordsRepository;
import com.example.task.services.RequestService;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestController {
    private final RequestService requestService;
    private final StopWordsRepository stopWordsRepository;

    public RequestController(RequestService requestService, StopWordsRepository stopWordsRepository) {
        this.requestService = requestService;
        this.stopWordsRepository = stopWordsRepository;
    }

    @GetMapping(path = "/main")
    public String getDefault() {
        if(stopWordsRepository.count() == 0){
            addStopWordsToBD();
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/templates/main/index.html"));

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            br.close();
        } catch (Exception e) {
        } finally {

        }

        return sb.toString();
    }
    @PostMapping(path = "/main")
    public void add (@RequestParam String str1, @RequestParam String str2){
        requestService.add(str1,str2);

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
