package ru.kpepskot.sport_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.kpepskot.sport_shop.constant.Role;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email", unique = true)
    private String userEmail;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role userRole;
    @Column(name = "user_password")
    private String password;
    private String image;
}
