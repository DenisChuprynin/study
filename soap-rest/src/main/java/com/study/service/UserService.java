package com.study.service;

import com.study.repository.model.User;
import com.study.rest.model.UserDTO;

public interface UserService {

    User findUser(Long id);

    void createUser(User userDTO);
}
