package ru.kpepskot.sport_shop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "key_product", unique = true)
    private long keyProduct;
    @NotNull
    @Column(name = "product_name")
    private String productName;
    private String description;
    private String maker;
    private long price;
    private String image;
}
