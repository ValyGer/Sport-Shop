package ru.kpepskot.sport_shop.dto.product;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpepskot.sport_shop.entity.Product;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class ProductInitDtoMapper {

    public Product productInitDtoToProduct(ProductInitDto productInitDto) {
        Product product = new Product();
        product.setKeyProduct(productInitDto.getKeyProduct());
        product.setProductName(productInitDto.getProductName());
        product.setDescription(productInitDto.getDescription());
        product.setMaker(productInitDto.getMaker());
        product.setPrice(Long.parseLong(productInitDto.getPrice()));

        Optional.ofNullable(productInitDto.getImage())
                .filter(Predicate.not(MultipartFile::isEmpty))
                .ifPresent(image -> product.setImage(image.getOriginalFilename()));

        return product;
    }
}
