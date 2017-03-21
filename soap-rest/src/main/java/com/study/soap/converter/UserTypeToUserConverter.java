package com.study.soap.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.study.com.soap.types.ObjectFactory;
import ru.study.com.soap.types.User;

/**
 * Created by denis on 21.03.2017.
 */
@Component
public class UserTypeToUserConverter implements Converter<User, com.study.repository.model.User> {

    private final ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public com.study.repository.model.User convert(User source) {
        return com.study.repository.model.User
                .builder()
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
