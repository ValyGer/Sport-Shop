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
@RequestMapping("users/rest/")
public class UserRestController {

    UserService userService;

    @GetMapping(value = "/{userId}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findAvatar(@PathVariable("userId") Long userId) {
        return userService.findAvatar(userId).orElseThrow(RuntimeException::new);
    }
}
