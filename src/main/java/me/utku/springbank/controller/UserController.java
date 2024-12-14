package me.utku.springbank.controller;

import me.utku.springbank.dto.user.UserChangePasswordRequest;
import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.generic.CrudController;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.User;
import me.utku.springbank.service.user.UserCrudService;
import me.utku.springbank.service.user.UserQueryService;
import me.utku.springbank.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends CrudController<User> {
    private final UserQueryService userQueryService;
    private final UserService userService;

    public UserController(UserCrudService userCrudService, UserQueryService userQueryService, UserService userService) {
        super(userCrudService);
        this.userQueryService = userQueryService;
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserDto getMe(@AuthenticationPrincipal User user) {
        return userQueryService.getMe(user.getId());
    }


    @PutMapping("/me/password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GenericResponse<UserDto>> changePassword(@AuthenticationPrincipal User user, @RequestBody UserChangePasswordRequest passwordChangeRequest) {
        return userService.changePassword(user, passwordChangeRequest).toResponseEntity();
    }
}