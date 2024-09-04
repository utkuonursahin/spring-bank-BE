package me.utku.springbank.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.utku.springbank.generic.BaseDto;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.User;
import me.utku.springbank.user.UserMapper;
import org.springframework.http.HttpStatus;
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
    private final UserMapper userMapper;

    public GenericResponse<BaseDto<User>> authenticate(LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        BaseDto<User> userDto = null;
        try {
            UsernamePasswordAuthenticationToken authToken = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.ssn(), loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(authToken);
            if (authentication.isAuthenticated()) {
                SecurityContext context = securityContextHolderStrategy.createEmptyContext();
                context.setAuthentication(authentication);
                securityContextHolderStrategy.setContext(context);
                securityContextRepository.saveContext(context, request, response);
                userDto = userMapper.toDto(((User) authentication.getPrincipal()));
            }
            return new GenericResponse<>(HttpStatus.OK.value(), "Authentication successful", userDto);
        } catch (Exception e) {
            throw new BadCredentialsException("Failed authentication with ssn:" + loginRequest.ssn());
        }
    }

    public GenericResponse<BaseDto<User>> checkSession(User user) {
        BaseDto<User> userDto = userMapper.toDto(user);
        return new GenericResponse<>(HttpStatus.OK.value(), "Session is valid", userDto);
    }
}
