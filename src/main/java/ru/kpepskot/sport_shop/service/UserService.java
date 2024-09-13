package ru.kpepskot.sport_shop.service;

import org.springframework.http.HttpStatus;
import ru.kpepskot.sport_shop.dto.UserDto;
import ru.kpepskot.sport_shop.dto.UserInitDto;
import ru.kpepskot.sport_shop.dto.UserInitUpdateDto;

public interface UserService {
    UserDto findUserById(Long id);

    UserDto createUser(UserInitDto userInitDto);

    UserDto updateUserById(Long userId, UserInitUpdateDto userInitUpdateDto);

    void deleteUserById(Long userId);
}
