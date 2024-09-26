package ru.kpepskot.sport_shop.dto.user;

import org.springframework.stereotype.Component;
import ru.kpepskot.sport_shop.entity.User;


@Component
public class UserDtoMapper {

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .image(user.getImage())
                .build();
    }
}
