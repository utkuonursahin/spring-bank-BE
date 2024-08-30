package me.utku.springbank.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserReadService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getUsers(){
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }

    public UserDto getUser(UUID id){
        return userRepository.findById(id).map(userMapper::userToUserDto).orElseThrow();
    }
}
