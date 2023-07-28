package com.example.task.entyties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StopWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String word;

    public StopWord(String word) {
        this.word = word;
    }
    public StopWord() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
