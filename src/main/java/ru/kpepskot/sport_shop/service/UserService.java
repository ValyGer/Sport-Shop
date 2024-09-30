package ru.kpepskot.sport_shop.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kpepskot.sport_shop.dto.user.UserDto;
import ru.kpepskot.sport_shop.dto.user.UserInitDto;
import ru.kpepskot.sport_shop.dto.user.UserInitUpdateDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto findUserById(Long id);

    UserDto createUser(UserInitDto userInitDto);

    UserDto updateUserById(Long userId, UserInitUpdateDto userInitUpdateDto);

    void deleteUserById(Long userId);

    List<UserDto> findAllUsers();

    Optional<byte[]> findAvatar(Long id);

    // Security!
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
