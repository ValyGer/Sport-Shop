package ru.kpepskot.sport_shop.dto.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    @Size(min = 3, max = 120, message = "Длинна имени должна быть не менее 3 и не более 120 символов")
    private String userName;
    @NotBlank
    @Size(min = 6, max = 12, message = "Длинна пароля должна быть не менее 6 и не более 12 символов")
    private String password;
}
