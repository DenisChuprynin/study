package com.study.soap.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.study.com.soap.types.ObjectFactory;
import ru.study.com.soap.types.User;

/**
 * Created by denis on 21.03.2017.
 */
@Component
public class UserToUserTypeConverter implements Converter<com.study.repository.model.User, User> {

    private final ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public User convert(com.study.repository.model.User source) {
        User user = objectFactory.createUser();
        user.setId(source.getId());
        user.setEmail(source.getEmail());
        user.setLastName(source.getLastName());
        user.setFirstName(source.getFirstName());
        user.setLogin(source.getLogin());
        user.setBirthDate(source.getBirthDate());
        return user;
    }
}
