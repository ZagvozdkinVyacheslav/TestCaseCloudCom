package com.example.task.repository;

import com.example.task.entyties.StopWord;
import org.springframework.data.repository.CrudRepository;

public interface StopWordsRepository extends CrudRepository<StopWord, Long> {
    long count();
    Iterable<StopWord> findAllById(Iterable<Long> longs);
}
