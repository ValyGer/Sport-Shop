package ru.kpepskot.sport_shop.dto.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserInitUpdateDto {

    private String userName;
    private String password;
    private MultipartFile image;
}
