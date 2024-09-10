package ru.kpepskot.sport_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpepskot.sport_shop.dto.UserDto;
import ru.kpepskot.sport_shop.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class TestUsersController {

    private final UserService userService;

    @GetMapping
    public String getCategory() {
        return "Добро пожаловать в тестовое пространство";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }



}
