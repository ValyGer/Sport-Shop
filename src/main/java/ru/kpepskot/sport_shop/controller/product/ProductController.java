package ru.kpepskot.sport_shop.controller.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpepskot.sport_shop.dto.product.ProductDto;
import ru.kpepskot.sport_shop.dto.product.ProductInitDto;
import ru.kpepskot.sport_shop.dto.product.ProductInitUpdateDto;
import ru.kpepskot.sport_shop.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    // Получение страницы списка всех товаров
    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", this.productService.findAllProducts());
        return "products/list_products";
    }

    // Получение страницы создания нового товара
    @GetMapping("/new")
    public String getProductPageCreate() {
        return "products/new_product";
    }

    // Создание нового товара и возврат на страницу товара
    @PostMapping("/new")
    public String createProduct(@Valid ProductInitDto productInitDto) {
        ProductDto productDto = this.productService.createProduct(productInitDto);
        return "redirect:/products/%d".formatted(productDto.getId());
    }

    // Получение страницы товара
    @GetMapping("/{productId:\\d+}")
    public String getProductById(Model model, @PathVariable("productId") Long productId) {
        model.addAttribute("product", this.productService.findProductById(productId));
        return "products/product";
    }

    // Получение страницы изменения пользователя
    @GetMapping("/{productId:\\d+}/edit")
    public String getProductPageEdit(Model model, @PathVariable("productId") Long productId) {
        model.addAttribute("product", this.productService.findProductById(productId));
        return "products/edit_product";
    }

    // Изменение данных товара
    @PostMapping("/{productId:\\d+}/edit")
    public String updateProductById(@PathVariable("productId") Long productId,
                                    @Valid ProductInitUpdateDto productInitUpdateDto) {
        this.productService.updateProductById(productId, productInitUpdateDto);
        return "redirect:/products/%d".formatted(productId);
    }

    // Удаление товара
    @PostMapping("/{productId:\\d+}/delete")
    public String deleteProductById(@PathVariable Long productId) {
        this.productService.deleteProductById(productId);
        return "redirect:/products";
    }
}
