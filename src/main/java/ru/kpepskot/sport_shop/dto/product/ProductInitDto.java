package ru.kpepskot.sport_shop.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductInitDto {
    @NotNull
    private long keyProduct;
    @NotNull
    @Size(min = 3, max = 256, message = "Длина названия должна быть не менее 3 и не более 1024 символов")
    private String productName;
    @Size(min = 8, max = 1024, message = "Длина описания должна быть не менее 0 и не более 1024 символов")
    private String description;
    @Size(min = 2, max = 256, message = "Длина наименования изготовителя должна быть не менее 8 и не более 1024 символов")
    private String maker;
    @NotNull
    private String price;
    private MultipartFile image;
}
