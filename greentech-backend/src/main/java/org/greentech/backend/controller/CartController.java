package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.response.CartResponseDto;
import org.greentech.backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/cart/products/{productId}")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponseDto> addProductToCart(@PathVariable(name = "productId") Integer productId) {
        return ResponseEntity
                .ok(cartService.addProductToCart(productId));
    }

    @DeleteMapping
    public ResponseEntity<CartResponseDto> removeProductFromCart(@PathVariable(name = "productId") Integer productId) {
        return ResponseEntity
                .ok(cartService.removeProductFromCart(productId));
    }
}
