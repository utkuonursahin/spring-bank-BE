package me.utku.springbank.user.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.auth.Role;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.user.User;
import me.utku.springbank.user.UserMapper;
import me.utku.springbank.user.UserRepository;
import me.utku.springbank.user.dto.UserDto;
import me.utku.springbank.user.dto.UserRegisterDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserCreateService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public GenericResponse<UserDto> createUser(UserRegisterDto userRegisterDto) {
        User registeredUser = userRepository.save(this.buildUserFromRegisterDto(userRegisterDto));
        return new GenericResponse<>(HttpStatus.CREATED.value(), "User registered successfully", userMapper.toDto(registeredUser));
    }

    private User buildUserFromRegisterDto(UserRegisterDto userRegisterDto) {
        return User.builder()
                .firstName(userRegisterDto.firstName())
                .lastName(userRegisterDto.lastName())
                .ssn(userRegisterDto.ssn())
                .password(passwordEncoder.encode(userRegisterDto.password()))
                .authorities(Set.of(Role.USER)).build();
    }
}

