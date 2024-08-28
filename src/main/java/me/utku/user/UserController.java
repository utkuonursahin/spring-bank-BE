package me.utku.user;

import me.utku.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //GET /api/v1/user - get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //GET /api/v1/user/{id} - get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    //POST /api/v1/user - create a new user
    @PostMapping
    public User createUser(@RequestBody NewUserRequest req) {
        User user = new User();
        user.setUsername(req.name());
        user.setPassword(req.password());
        user.setBalance(0);
        user.setDebt(0);
        return userRepository.save(user);
    }

    //PATCH /api/v1/user/{id} - update user by id
    @PatchMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody NewUserRequest req) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        user.setUsername(req.name() == null ? user.getUsername() : req.name());
        //user.setPassword(req.password()); //DON'T UPDATE PASSWORD WITH THIS METHOD
        return userRepository.save(user);
    }

    //DELETE /api/v1/user/{id} - delete user by id
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}