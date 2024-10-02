package ru.kpepskot.sport_shop.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpepskot.sport_shop.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserRestController {

    UserService userService;

    @GetMapping(value = "/rest/{userId}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findAvatar(@PathVariable("userId") Long userId) {
        return userService.findAvatar(userId).orElseThrow(RuntimeException::new);
    }

    @GetMapping(value = "/{userId}/rest/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findAvatarForUpdate(@PathVariable("userId") Long userId) {
        return userService.findAvatar(userId).orElseThrow(RuntimeException::new);
    }
}
