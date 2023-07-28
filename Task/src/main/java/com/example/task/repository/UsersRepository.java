package com.example.task.repository;

import com.example.task.entyties.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Long> {
}
