package ru.kpepskot.sport_shop.dto;

import org.springframework.stereotype.Component;
import ru.kpepskot.sport_shop.entity.User;


@Component
public class UserDtoMapper {

    public UserDto UserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
