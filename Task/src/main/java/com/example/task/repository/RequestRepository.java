package com.example.task.repository;

import com.example.task.entyties.PostRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<PostRequest, Long> {
    List<PostRequest> findAllByUserId(long userId);
    void deleteAllByUserId(long userId);
}
