package ru.kpepskot.sport_shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.kpepskot.sport_shop.constant.Role;
import ru.kpepskot.sport_shop.dto.user.*;
import ru.kpepskot.sport_shop.entity.User;
import ru.kpepskot.sport_shop.error.NotFoundException;
import ru.kpepskot.sport_shop.repository.UserRepository;
import ru.kpepskot.sport_shop.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserInitDtoMapper userInitDtoMapper;
    private final ImageServiceImpl imageService;

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
        User user = userInitDtoMapper.UserInitDtoToUser(userInitDto);
        user.setUserRole(Role.USER.toString());
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
            if (userInitUpdateDto.getUserName() != null) {
                user.setUserName(userInitUpdateDto.getUserName());
            }
            if (userInitUpdateDto.getPassword() != null) {
                user.setPassword(userInitUpdateDto.getPassword());
            }
            if (userInitUpdateDto.getImage() != null) {
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
}