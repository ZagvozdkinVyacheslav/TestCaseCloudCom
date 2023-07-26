package com.example.task.requests;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostRequest {

    private long id;
    private long userId;
    private String outputString;
    private String outerValue;




    private String Algorithm(String str){
        return str + str;
    }
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getOutputString() {
        return outputString;
    }

    public String getOuterValue() {
        return outerValue;
    }

    @Override
    public String toString() {
        return "PostRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", outputString='" + outputString + '\'' +
                ", outerValue='" + outerValue + '\'' +
                '}';
    }
}
