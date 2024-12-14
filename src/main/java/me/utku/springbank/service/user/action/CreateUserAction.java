package me.utku.springbank.service.user.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.dto.user.UserRegisterDto;
import me.utku.springbank.enums.auth.Role;
import me.utku.springbank.mapper.UserMapper;
import me.utku.springbank.model.User;
import me.utku.springbank.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CreateUserAction {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserDto execute(UserRegisterDto userRegisterDto) {
        return userMapper.toDto(userRepository.save(this.buildUserFromRegisterDto(userRegisterDto)));
    }

    private User buildUserFromRegisterDto(UserRegisterDto userRegisterDto) {
        return User.builder()
                .firstName(userRegisterDto.firstName())
                .lastName(userRegisterDto.lastName())
                .ssn(userRegisterDto.ssn())
                .password(passwordEncoder.encode(userRegisterDto.password()))
                .authorities(Set.of(Role.ROLE_USER)).build();
    }
}
