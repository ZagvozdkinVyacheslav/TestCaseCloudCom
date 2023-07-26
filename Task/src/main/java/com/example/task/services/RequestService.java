package com.example.task.services;


import com.example.task.requests.PostRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    private ArrayList<PostRequest> lst = new ArrayList<>();
    public List<PostRequest> list(){
        return lst;
    }

    public void add(PostRequest postRequest) {
        lst.add(postRequest);
    }
}
