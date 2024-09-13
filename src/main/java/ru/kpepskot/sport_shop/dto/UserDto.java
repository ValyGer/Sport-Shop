package ru.kpepskot.sport_shop.dto;

import lombok.*;

import javax.validation.Valid;

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
}
