package ru.kpepskot.sport_shop.service;

import ru.kpepskot.sport_shop.dto.user.UserDto;
import ru.kpepskot.sport_shop.dto.user.UserInitDto;
import ru.kpepskot.sport_shop.dto.user.UserInitUpdateDto;

import java.util.List;

public interface UserService {
    UserDto findUserById(Long id);

    UserDto createUser(UserInitDto userInitDto);

    UserDto updateUserById(Long userId, UserInitUpdateDto userInitUpdateDto);

    void deleteUserById(Long userId);

    List<UserDto> findAllUsers();
}
