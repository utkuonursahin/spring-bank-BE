package me.utku.springbank.user;

import me.utku.springbank.generic.CrudController;
import me.utku.springbank.user.dto.UserDto;
import me.utku.springbank.user.service.UserCrudService;
import me.utku.springbank.user.service.UserQueryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends CrudController<User> {
    private final UserQueryService userQueryService;

    public UserController(UserCrudService userCrudService, UserQueryService userQueryService) {
        super(userCrudService);
        this.userQueryService = userQueryService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserDto getMe(@AuthenticationPrincipal User user) {
        return userQueryService.getMe(user.getId());
    }

}