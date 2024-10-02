package ru.kpepskot.sport_shop.dto.product;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private long id;
    private long keyProduct;
    private String productName;
    private String description;
    private String maker;
    private long price;
    private String image;
}
