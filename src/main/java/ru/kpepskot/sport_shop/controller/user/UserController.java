package ru.kpepskot.sport_shop.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpepskot.sport_shop.dto.user.UserDto;
import ru.kpepskot.sport_shop.dto.user.UserInitDto;
import ru.kpepskot.sport_shop.dto.user.UserInitUpdateDto;
import ru.kpepskot.sport_shop.service.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Получение всех пользователей
    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", this.userService.findAllUsers());
        return "users/list";
    }

    // Получение данных пользователя
    @GetMapping("/{userId}")
    public String getUserById(Model model, @PathVariable Long userId) {
        model.addAttribute("users/id", this.userService.findUserById(userId));
        return "users/id";
    }

    //     Получение страницы создания пользователя
    @GetMapping("/new")
    public String getUserPageCreate() {
        return "users/new_user";
    }

    //     Создание нового пользователя
    @PostMapping("/new")
    public String createUser(@Valid UserInitDto userInitDto) {
        this.userService.createUser(userInitDto);
        return "redirect : users/list";
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
