package me.utku.springbank.user;

import me.utku.springbank.generic.CrudController;
import me.utku.springbank.user.service.UserCrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends CrudController<User> {

    public UserController(UserCrudService userCrudService) {
        super(userCrudService);
    }
}