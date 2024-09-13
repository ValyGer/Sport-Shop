package ru.kpepskot.sport_shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpepskot.sport_shop.dto.UserDto;
import ru.kpepskot.sport_shop.dto.UserInitDto;
import ru.kpepskot.sport_shop.dto.UserInitUpdateDto;
import ru.kpepskot.sport_shop.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getCategory() {
        return "Добро пожаловать в тестовое пространство!";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(userId));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserInitDto userInitDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userInitDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long userId,
                                                  @RequestBody UserInitUpdateDto userInitUpdateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserById(userId, userInitUpdateDto));
    }

    @DeleteMapping("/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return HttpStatus.NO_CONTENT;
    }
}
