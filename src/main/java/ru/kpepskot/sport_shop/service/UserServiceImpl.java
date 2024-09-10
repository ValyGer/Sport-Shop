package ru.kpepskot.sport_shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpepskot.sport_shop.dto.UserDto;
import ru.kpepskot.sport_shop.dto.UserDtoMapper;
import ru.kpepskot.sport_shop.entity.User;
import ru.kpepskot.sport_shop.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDto findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return userDtoMapper.UserToUserDto(optionalUser.get());
        } else {
            throw new RuntimeException("User not found!");
        }
    }
}