package ru.kpepskot.sport_shop.dto.user;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private long id;
    private String userEmail;
    private String userName;
    private String image;
}
