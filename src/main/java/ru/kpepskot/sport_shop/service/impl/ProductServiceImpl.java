package ru.kpepskot.sport_shop.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.kpepskot.sport_shop.dto.product.*;
import ru.kpepskot.sport_shop.entity.Product;
import ru.kpepskot.sport_shop.error.InvalidRequestException;
import ru.kpepskot.sport_shop.error.NotFoundException;
import ru.kpepskot.sport_shop.repository.ProductRepository;
import ru.kpepskot.sport_shop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;
    private final ProductInitDtoMapper productInitDtoMapper;
    private final ImageServiceImpl imageService;

    @Override
    public ProductDto findProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return productDtoMapper.productToProductDto(optionalProduct.get());
        } else {
            throw new NotFoundException("Пользователь с id = " + id + " не был найден");
        }
    }

    public Optional<byte[]> findAvatar(Long id) {
        return productRepository.findById(id).map(Product::getImage).filter(StringUtils::hasText).flatMap(imageService::get);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductInitDto productInitDto) {
        Product product = productInitDtoMapper.productInitDtoToProduct(productInitDto);
        saveOnDisk(productInitDto.getImage());
        return productDtoMapper.productToProductDto(productRepository.save(product));
    }

    @SneakyThrows
    private void saveOnDisk(MultipartFile image) {
        if (!image.isEmpty()) {
            imageService.upload(image.getOriginalFilename(), image.getInputStream());
        }
    }

    @Override
    @Transactional
    public ProductDto updateProductById(Long productId, ProductInitUpdateDto productInitUpdateDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if (!productInitUpdateDto.getProductName().isEmpty()) {
                if ((productInitUpdateDto.getProductName().length() >= 3)
                        && (productInitUpdateDto.getProductName().length() < 256)) {
                    product.setProductName(productInitUpdateDto.getProductName());
                } else {
                    throw new InvalidRequestException("Длина названия должна быть не менее 3 и не более 256 символов");
                }
            }
            if (!productInitUpdateDto.getDescription().isEmpty()) {
                if ((productInitUpdateDto.getDescription().length() >= 3)
                        && (productInitUpdateDto.getDescription().length() < 120)) {
                    product.setDescription(productInitUpdateDto.getDescription());
                } else {
                    throw new InvalidRequestException("Длина описания должна быть не менее 8 и не более 1024 символов");
                }
            }
            if (!productInitUpdateDto.getDescription().isEmpty()) {
                product.setPrice(Long.parseLong(productInitUpdateDto.getPrice()));
            }
            if (!productInitUpdateDto.getImage().isEmpty()) {
                saveOnDisk(productInitUpdateDto.getImage());
                product.setImage(productInitUpdateDto.getImage().getOriginalFilename());
            }
            return productDtoMapper.productToProductDto(productRepository.save(product));
        } else {
            throw new NotFoundException("Пользователь с id = " + productId + " не был найден");
        }
    }

    @Override
    public void deleteProductById(Long productId) {
        findProductById(productId);
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : productList) {
            ProductDto productDto = productDtoMapper.productToProductDto(product);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }
}
