package ru.kpepskot.sport_shop.service;

import ru.kpepskot.sport_shop.dto.UserDto;
import ru.kpepskot.sport_shop.dto.UserInitDto;

public interface UserService {
    UserDto findUserById(Long id);

    UserDto createUser(UserInitDto userInitDto);
}
