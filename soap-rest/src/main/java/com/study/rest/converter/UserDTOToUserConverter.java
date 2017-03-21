package com.study.rest.converter;

import com.study.repository.model.User;
import com.study.rest.model.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {

    @Override
    public User convert(UserDTO source) {
        return User.builder()
                .id(source.getId())
                .login(source.getLogin())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .password(source.getPassword())
                .birthDate(source.getBirthDate())
                .build();
    }
}
