package ru.kpepskot.sport_shop.service;

import ru.kpepskot.sport_shop.dto.UserDto;

public interface UserService {
    UserDto findById(Long id);
}
