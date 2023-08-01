package com.example.task.repository;

import com.example.task.entyties.Authority;
import com.example.task.entyties.User;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    Authority findAuthorityByUsername(String username);
}
