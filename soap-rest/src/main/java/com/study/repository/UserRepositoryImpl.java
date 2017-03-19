package com.study.repository;

import com.study.repository.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoryImpl extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);
}
