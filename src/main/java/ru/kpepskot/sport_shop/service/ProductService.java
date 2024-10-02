package ru.kpepskot.sport_shop.service;

import jakarta.transaction.Transactional;
import ru.kpepskot.sport_shop.dto.product.ProductDto;
import ru.kpepskot.sport_shop.dto.product.ProductInitDto;
import ru.kpepskot.sport_shop.dto.product.ProductInitUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDto> findAllProducts();

    ProductDto createProduct(ProductInitDto productInitDto);

    ProductDto findProductById(Long id);

    Optional<byte[]> findAvatar(Long id);

    @Transactional
    ProductDto updateProductById(Long productId, ProductInitUpdateDto productInitUpdateDto);

    void deleteProductById(Long productId);
}
