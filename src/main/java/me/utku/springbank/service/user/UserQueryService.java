package me.utku.springbank.service.user;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.mapper.UserMapper;
import me.utku.springbank.repository.UserRepository;
import me.utku.springbank.dto.user.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String ssn) throws UsernameNotFoundException {
        return userRepository.findBySsn(ssn).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public UserDto getMe(UUID id) {
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
