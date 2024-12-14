package me.utku.springbank.service.user;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.user.UserChangePasswordRequest;
import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.dto.user.UserRegisterDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.User;
import me.utku.springbank.service.user.action.ChangePasswordAction;
import me.utku.springbank.service.user.action.CreateUserAction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final CreateUserAction createUserAction;
    private final ChangePasswordAction changePasswordAction;

    public GenericResponse<UserDto> createUser(UserRegisterDto userRegisterDto) {
        UserDto registeredUser = createUserAction.execute(userRegisterDto);
        return GenericResponse.ok(HttpStatus.CREATED.value(), registeredUser);
    }

    public GenericResponse<UserDto> changePassword(User user, UserChangePasswordRequest userChangePasswordRequest) {
        return changePasswordAction.execute(user, userChangePasswordRequest);
    }
}
