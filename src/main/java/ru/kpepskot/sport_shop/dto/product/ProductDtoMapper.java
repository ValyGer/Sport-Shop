package ru.kpepskot.sport_shop.dto.product;

import org.springframework.stereotype.Component;
import ru.kpepskot.sport_shop.entity.Product;

@Component
public class ProductDtoMapper {
    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .keyProduct(product.getKeyProduct())
                .productName(product.getProductName())
                .description(product.getDescription())
                .maker(product.getMaker())
                .price(product.getPrice())
                .image(product.getImage())
                .build();
    }
}
