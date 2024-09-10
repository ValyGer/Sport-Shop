package ru.kpepskot.sport_shop.entity;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;

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
    @Column(unique = true)
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "user_password")
    private String password;
}
