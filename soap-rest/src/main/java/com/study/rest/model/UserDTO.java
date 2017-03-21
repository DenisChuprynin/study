package com.study.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

/**
 * Created by denis on 19.03.2017.
 */
@Data
@Builder
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class UserDTO {

    @ApiParam(value = "Идентификатор пользователя")
    private Long id;

    @ApiParam(value = "Логин")
    private String login;

    @ApiParam(value = "Пароль")
    @JsonIgnore
    private String password;

    @ApiParam(value = "Фамилия")
    private String firstName;

    @ApiParam(value = "Идентификатор")
    private String lastName;

    @ApiParam(value = "Элленктронный почтовый адрес")
    @Email
    private String email;

    @ApiParam(value = "Дата рождения в милисекундах")
    private Long birthDate;


}
