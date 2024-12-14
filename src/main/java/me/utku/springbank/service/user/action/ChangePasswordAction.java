package me.utku.springbank.service.user.action;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.dto.user.UserChangePasswordRequest;
import me.utku.springbank.dto.user.UserDto;
import me.utku.springbank.exception.OperationDeniedException;
import me.utku.springbank.generic.GenericResponse;
import me.utku.springbank.mapper.UserMapper;
import me.utku.springbank.model.User;
import me.utku.springbank.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordAction {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public GenericResponse<UserDto> execute(User user, UserChangePasswordRequest userChangePasswordRequest) {
        if (!passwordEncoder.matches(userChangePasswordRequest.oldPassword(), user.getPassword())) {
            throw OperationDeniedException.of("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(userChangePasswordRequest.newPassword()));
        userRepository.save(user);
        return GenericResponse.ok(HttpStatus.OK.value(), userMapper.toDto(user));
    }

}
