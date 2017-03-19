package com.study.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by denis on 19.03.2017.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
