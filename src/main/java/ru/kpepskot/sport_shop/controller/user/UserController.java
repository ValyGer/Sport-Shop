package ru.kpepskot.sport_shop.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpepskot.sport_shop.dto.user.UserDto;
import ru.kpepskot.sport_shop.dto.user.UserInitDto;
import ru.kpepskot.sport_shop.dto.user.UserInitUpdateDto;
import ru.kpepskot.sport_shop.service.UserService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Получение страницы списка всех пользователей
    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", this.userService.findAllUsers());
        return "users/list";
    }

    // Получение страницы создания нового пользователя
    @GetMapping("/new")
    public String getUserPageCreate() {
        return "users/new_user";
    }

    // Создание нового пользователя и возврат на страницу пользователя
    @PostMapping("/new")
    public String createUser(@Valid UserInitDto userInitDto) {
        UserDto userDto = this.userService.createUser(userInitDto);
        return "redirect:/users/%d".formatted(userDto.getId());
    }

    // Получение страницы пользователя
    @GetMapping("/{userId:\\d+}")
    public String getUserById(Model model, @PathVariable("userId") Long userId) {
        model.addAttribute("user", this.userService.findUserById(userId));
        return "users/user";
    }

    // Получение страницы изменения пользователя
    @GetMapping("/{userId:\\d+}/edit")
    public String getUserPageEdit(Model model, @PathVariable("userId") Long userId) {
        model.addAttribute("user", this.userService.findUserById(userId));
        return "users/edit_user";
    }

    // Изменение данных пользователя
    @PostMapping("/{userId:\\d+}/edit")
    public String updateUserById(@PathVariable("userId") Long userId, @Valid UserInitUpdateDto userInitUpdateDto) {
        this.userService.updateUserById(userId, userInitUpdateDto);
        return "redirect:/users/%d".formatted(userId);
    }

    // Удаление пользователя
    @PostMapping("/{userId:\\d+}/delete")
    public String deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);
        return "redirect:/users";
    }
}
