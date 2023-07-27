package com.example.task.controllers;

import com.example.task.requests.PostRequest;
import com.example.task.services.RequestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

@RestController
@RequestMapping(value = "requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(path = "get")
    public List<PostRequest> list(){
        return requestService.list();
    }
    @GetMapping(path = "/main")
    public String getDefault() {
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
    public void add(@RequestParam String str1, @RequestParam String str2){
        requestService.add(str1,str2);

    }


}
