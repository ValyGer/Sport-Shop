package ru.kpepskot.sport_shop.dto;

import org.springframework.stereotype.Component;
import ru.kpepskot.sport_shop.entity.User;


@Component
public class UserDtoMapper {

    public UserDto UserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .build();
    }
}
