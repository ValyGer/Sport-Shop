package ru.kpepskot.sport_shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.kpepskot.sport_shop.constant.Role;
import ru.kpepskot.sport_shop.dto.user.*;
import ru.kpepskot.sport_shop.entity.User;
import ru.kpepskot.sport_shop.error.InvalidRequestException;
import ru.kpepskot.sport_shop.error.NotFoundException;
import ru.kpepskot.sport_shop.repository.UserRepository;
import ru.kpepskot.sport_shop.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserInitDtoMapper userInitDtoMapper;
    private final ImageServiceImpl imageService;
    private final PasswordEncoder encoder;

    @Override
    public UserDto findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return userDtoMapper.userToUserDto(optionalUser.get());
        } else {
            throw new NotFoundException("Пользователь с id = " + id + " не был найден");
        }
    }

    public Optional<byte[]> findAvatar(Long id) {
        return userRepository.findById(id).map(User::getImage).filter(StringUtils::hasText).flatMap(imageService::get);
    }

    @Override
    @Transactional
    public UserDto createUser(UserInitDto userInitDto) {
        User user = userInitDtoMapper.userInitDtoToUser(userInitDto);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserRole(Role.USER);
        saveOnDisk(userInitDto.getImage());
        return userDtoMapper.userToUserDto(userRepository.save(user));
    }

    @SneakyThrows
    private void saveOnDisk(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Override
    @Transactional
    public UserDto updateUserById(Long userId, UserInitUpdateDto userInitUpdateDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!userInitUpdateDto.getUserName().isEmpty()) {
                if ((userInitUpdateDto.getUserName().length() >= 3) && (userInitUpdateDto.getUserName().length() < 120)) {
                    user.setUserName(userInitUpdateDto.getUserName());
                } else {
                    throw new InvalidRequestException("Длина имени должна быть не менее 3 и не более 120 символов");
                }
            }
            if (!userInitUpdateDto.getPassword().isEmpty()) {
                if ((userInitUpdateDto.getPassword().length() >= 3) && (userInitUpdateDto.getPassword().length() < 120)) {
                    user.setPassword(encoder.encode(userInitUpdateDto.getPassword()));
                } else {
                    throw new InvalidRequestException("Длина пароля должна быть не менее 3 и не более 120 символов");
                }
            }
            if (!userInitUpdateDto.getImage().isEmpty()) {
                saveOnDisk(userInitUpdateDto.getImage());
                user.setImage(userInitUpdateDto.getImage().getOriginalFilename());
            }
            return userDtoMapper.userToUserDto(userRepository.save(user));
        } else {
            throw new NotFoundException("Пользователь с id = " + userId + " не был найден");
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        findUserById(userId);
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = userDtoMapper.userToUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    // Security!
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // создаем пользователя SpringSecurity
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    Collections.singleton(user.getUserRole()));
        } else {
            throw new RuntimeException("Пользователь с именем " + username + " не найден");
        }
    }
}