package com.study.soap;

import com.study.service.UserService;
import com.study.soap.converter.UserToUserTypeConverter;
import com.study.soap.converter.UserTypeToUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.study.com.soap.types.LongTypeRequest;
import ru.study.com.soap.types.ObjectFactory;
import ru.study.com.soap.types.User;

import javax.xml.bind.JAXBElement;

/**
 * Created by denis on 21.03.2017.
 */
@Endpoint
public class SoapRestEndpoint {

    private static final String NAMESPACE_URI = "http://com.study.ru/soap/types";

    private final UserService userService;
    private final UserToUserTypeConverter userToUserTypeConverter;
    private final UserTypeToUserConverter userTypeToUserConverter;

    @Autowired
    public SoapRestEndpoint(UserService userService,
                            UserToUserTypeConverter userToUserTypeConverter,
                            UserTypeToUserConverter userTypeToUserConverter) {
        this.userService = userService;
        this.userToUserTypeConverter = userToUserTypeConverter;
        this.userTypeToUserConverter = userTypeToUserConverter;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetUserRequest")
    @ResponsePayload
    public User getUser(@RequestPayload JAXBElement<LongTypeRequest> request) {
        return userToUserTypeConverter.convert(userService.findUser(request.getValue().getId()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUser")
    public void deleteUser(@RequestPayload Long id) {
        userService.deleteUser(id);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUser")
    public void createUser(@RequestPayload User user) {
        userService.createUser(userTypeToUserConverter.convert(user));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUser")
    public void updateUser(@RequestPayload User user) {
        userService.updateUser(userTypeToUserConverter.convert(user));
    }
}
