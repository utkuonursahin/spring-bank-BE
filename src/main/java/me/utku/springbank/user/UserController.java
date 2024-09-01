package me.utku.springbank.user;

import me.utku.springbank.generic.CrudController;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.service.UserCreateService;
import me.utku.springbank.user.service.UserCrudService;
import me.utku.springbank.user.service.UserReadService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends CrudController<User> {
    private final UserReadService userReadService;
    private final UserCreateService userCreateService;

    public UserController(UserCrudService userCrudService, UserReadService userReadService, UserCreateService userCreateService) {
        super(userCrudService);
        this.userReadService = userReadService;
        this.userCreateService = userCreateService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserDto getMe(@AuthenticationPrincipal User user) {
        return userReadService.getMe(user.getId());
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<UserDto>> register(UserRegisterDto user) {
        return userCreateService.registerUser(user).toResponseEntity();
    }

}