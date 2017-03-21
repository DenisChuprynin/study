package com.study.rest;

import com.study.rest.converter.UserDTOToUserConverter;
import com.study.rest.converter.UserToUserDTOConverter;
import com.study.rest.model.UserDTO;
import com.study.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDTOToUserConverter userDTOToUserConverter;
    @Autowired
    private UserToUserDTOConverter userToUserDTOConverter;

    @ApiOperation(value = "Получение данных пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь найден"),
            @ApiResponse(code = 404, message = "Пользователь не найден")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                userToUserDTOConverter.convert(userService.findUser(id))
        );
    }

    @ApiOperation(value = "Удаление пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь удален"),
            @ApiResponse(code = 404, message = "Пользователь не найден")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Получение данных пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь создан"),
            @ApiResponse(code = 400, message = "Не коректные данные"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
    })
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.createUser(userDTOToUserConverter.convert(userDTO));
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "Обновление данных пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь обновлен"),
            @ApiResponse(code = 400, message = "Не коректные данные"),
            @ApiResponse(code = 500, message = "Внутренняя ошибка сервера")
    })
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody @Valid UserDTO userDTO) {
        userService.updateUser(userDTOToUserConverter.convert(userDTO));
        return new ResponseEntity(HttpStatus.OK);
    }

}
