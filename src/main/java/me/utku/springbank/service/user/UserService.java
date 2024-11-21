package me.utku.springbank.service.user;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.dto.user.UserRegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CreateUserService createUserService;

    public GenericResponse<UserDto> createUser(UserRegisterDto userRegisterDto) {
        UserDto registeredUser = createUserService.createUser(userRegisterDto);
        return GenericResponse.ok(HttpStatus.CREATED.value(), registeredUser);
    }
}
