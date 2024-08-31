package me.utku.springbank.user.service;

import lombok.RequiredArgsConstructor;
import me.utku.springbank.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String ssn) throws UsernameNotFoundException {
        return userRepository.findBySsn(ssn).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
