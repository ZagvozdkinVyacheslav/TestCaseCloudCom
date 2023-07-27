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

    public void add(String str1, String str2) {
        PostRequest postRequest = new PostRequest(1,1,Algorithm(str1),Algorithm(str2),outerValueAlg(str1, str2));
        lst.add(postRequest);
    }
    private String Algorithm(String str){

        return str + str;
    }
    private String outerValueAlg(String str1,String str2){

        return Integer.toString(str1.length() + str2.length()) + "%" ;
    }
}
