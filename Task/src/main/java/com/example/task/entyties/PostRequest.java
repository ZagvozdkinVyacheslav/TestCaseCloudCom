package com.example.task.entyties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@Entity
public class PostRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private String str1;
    private String str2;
    private String outerValue;

    public PostRequest(String str1, String str2, String outerValue) {
        this.str1 = str1;
        this.str2 = str2;
        this.outerValue = outerValue;
    }

    public PostRequest(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getOuterValue() {
        return outerValue;
    }

    public void setOuterValue(String outerValue) {
        this.outerValue = outerValue;
    }

    @Override
    public String toString() {
        return "PostRequest{" +
                "id=" + id +
                ", userId=" + userId +
                ", str1='" + str1 + '\'' +
                ", str2='" + str2 + '\'' +
                ", outerValue='" + outerValue + '\'' +
                '}';
    }
}
