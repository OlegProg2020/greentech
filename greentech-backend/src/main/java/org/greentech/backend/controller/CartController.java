package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.response.CartResponseDto;
import org.greentech.backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/carts/{cartId}/products/{productId}")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponseDto> addProductToCart(@PathVariable(name = "cartId") Integer cartId,
                                                            @PathVariable(name = "productId") Integer productId) {
        return ResponseEntity
                .ok(cartService.addProduct(cartId, productId));
    }

    @DeleteMapping
    public ResponseEntity<CartResponseDto> removeProductFromCart(@PathVariable(name = "cartId") Integer cartId,
                                                                 @PathVariable(name = "productId") Integer productId) {
        return ResponseEntity
                .ok(cartService.removeProduct(cartId, productId));
    }
}
