package me.utku.springbank.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.auth.LoginRequestDto;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.model.User;
import me.utku.springbank.mapper.UserMapper;
import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.dto.user.UserRegisterDto;
import me.utku.springbank.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    private final UserService userService;
    private final UserMapper userMapper;

    public GenericResponse<BaseDto<User>> authenticate(LoginRequestDto loginRequestDto, HttpServletRequest request, HttpServletResponse response) {
        BaseDto<User> userDto = null;
        try {
            UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.unauthenticated(loginRequestDto.ssn(), loginRequestDto.password());
            Authentication authentication = authenticationManager.authenticate(authToken);
            if (authentication.isAuthenticated()) {
                SecurityContext context = securityContextHolderStrategy.createEmptyContext();
                context.setAuthentication(authentication);
                securityContextHolderStrategy.setContext(context);
                securityContextRepository.saveContext(context, request, response);
                userDto = userMapper.toDto(((User) authentication.getPrincipal()));
            }
            return GenericResponse.ok(userDto);
        } catch (Exception e) {
            throw new BadCredentialsException("Failed authentication with ssn:" + loginRequestDto.ssn());
        }
    }

    public GenericResponse<UserDto> registerUser(UserRegisterDto userRegisterDto) {
        return userService.createUser(userRegisterDto);
    }

    public GenericResponse<BaseDto<User>> checkSession(User user) {
        BaseDto<User> userDto = userMapper.toDto(user);
        return GenericResponse.ok(userDto);
    }
}
