package ru.kpepskot.sport_shop.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserInitDto {
    @Email
    @NotBlank
    private String userEmail;
    @NotBlank
    @Size(min = 3, max = 120, message = "Длина имени должна быть не менее 3 и не более 120 символов")
    private String userName;
    @NotBlank
    @Size(min = 3, max = 12, message = "Длина пароля должна быть не менее 3 и не более 12 символов")
    private String password;
    private MultipartFile image;
}
