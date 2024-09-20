package ru.kpepskot.sport_shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpepskot.sport_shop.constant.Role;
import ru.kpepskot.sport_shop.dto.user.*;
import ru.kpepskot.sport_shop.entity.User;
import ru.kpepskot.sport_shop.error.NotFoundException;
import ru.kpepskot.sport_shop.repository.UserRepository;
import ru.kpepskot.sport_shop.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserInitDtoMapper userInitDtoMapper;

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return userDtoMapper.userToUserDto(optionalUser.get());
        } else {
            throw new NotFoundException("Пользователь с id = " + id + " не был найден");
        }
    }

    @Override
    @Transactional
    public UserDto createUser(UserInitDto userInitDto) {
        User user = userInitDtoMapper.UserInitDtoToUser(userInitDto);
        user.setUserRole(Role.USER.toString());
        return userDtoMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto updateUserById(Long userId, UserInitUpdateDto userInitUpdateDto) {
        return null;
    }


    @Override
    public void deleteUserById(Long userId) {
       findUserById(userId);
       userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(userDtoMapper::userToUserDto).collect(Collectors.toList());
    }
}