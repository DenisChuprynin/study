package com.study.service;

import com.study.exception.UserException;
import com.study.exception.UserNotFoundException;
import com.study.repository.UserRepositoryImpl;
import com.study.repository.model.User;
import com.study.rest.converter.UserDTOToUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepositoryImpl;
    private final UserDTOToUserConverter userDTOToUserConverter;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepositoryImpl,
                           UserDTOToUserConverter userDTOToUserConverter) {
        this.userRepositoryImpl = userRepositoryImpl;
        this.userDTOToUserConverter = userDTOToUserConverter;
    }

    @Override
    public void createUser(User user) {
        if (user.getId() != null) {
            throw new UserException("Идентификатор пользователя должен быть пустым!");
        }
        userRepositoryImpl.save(user);
    }

    @Override
    public User findUser(Long id) {
        return userRepositoryImpl.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

}
