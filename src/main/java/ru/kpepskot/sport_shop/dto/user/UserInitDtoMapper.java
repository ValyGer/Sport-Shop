package ru.kpepskot.sport_shop.dto.user;

import org.springframework.stereotype.Component;
import ru.kpepskot.sport_shop.entity.User;

@Component
public class UserInitDtoMapper {

    public User UserInitDtoToUser(UserInitDto userInitDto) {
        User user = new User();
        user.setUserEmail(userInitDto.getUserEmail());
        user.setUserName(userInitDto.getUserName());
        user.setPassword(userInitDto.getPassword());
        return user;
    }
}
