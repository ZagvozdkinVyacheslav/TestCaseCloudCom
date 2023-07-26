package com.example.task.controllers;

import com.example.task.requests.PostRequest;
import com.example.task.services.RequestService;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(path = "post")
    public List<PostRequest> add(@RequestBody PostRequest postRequest){
        requestService.add(postRequest);
        return requestService.list();
    }
}
