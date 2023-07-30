package com.example.task.repository;

import com.example.task.entyties.PostRequest;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<PostRequest, Long> {
}
