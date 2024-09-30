package ru.kpepskot.sport_shop.controller.product;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpepskot.sport_shop.service.ProductService;

@RestController
@AllArgsConstructor
@RequestMapping("products/rest/")
public class ProductRestController {

    ProductService productService;

    @GetMapping(value = "/{productId}/avatar", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] findAvatar(@PathVariable("productId") Long productId) {
        return productService.findAvatar(productId).orElseThrow(RuntimeException::new);
    }
}
