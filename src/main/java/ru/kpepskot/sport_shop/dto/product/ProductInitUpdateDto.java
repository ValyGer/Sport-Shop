package ru.kpepskot.sport_shop.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductInitUpdateDto {

    private String productName;
    private String description;
    private String price;
    private MultipartFile image;
}
