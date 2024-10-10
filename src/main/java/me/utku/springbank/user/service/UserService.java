package me.utku.springbank.user.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.dto.UserDto;
import me.utku.springbank.user.dto.UserRegisterDto;
import me.utku.springbank.user.service.action.CreateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CreateUserService createUserService;

    public GenericResponse<UserDto> createUser(UserRegisterDto userRegisterDto) {
        UserDto registeredUser = createUserService.createUser(userRegisterDto);
        return new GenericResponse<>(HttpStatus.CREATED.value(), registeredUser);
    }
}
