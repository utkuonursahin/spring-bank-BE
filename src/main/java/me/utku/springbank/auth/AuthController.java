package me.utku.springbank.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.User;
import me.utku.springbank.user.dto.UserDto;
import me.utku.springbank.user.dto.UserRegisterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/check-session")
    public ResponseEntity<GenericResponse<BaseDto<User>>> checkSession(@AuthenticationPrincipal User user) {
        GenericResponse<BaseDto<User>> authResponse = authService.checkSession(user);
        return authResponse.toResponseEntity();
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<BaseDto<User>>> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        GenericResponse<BaseDto<User>> authResponse = authService.authenticate(loginRequest, httpServletRequest, httpServletResponse);
        return authResponse.toResponseEntity();
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<UserDto>> register(@RequestBody UserRegisterDto user) {
        return authService.registerUser(user).toResponseEntity();
    }
}
