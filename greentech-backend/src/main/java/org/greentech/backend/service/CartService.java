package org.greentech.backend.service;

import jakarta.validation.constraints.Min;
import org.greentech.backend.dto.response.CartResponseDto;

public interface CartService {

    CartResponseDto addProductToCart(@Min(value = 1, message = "productId должен быть >= 1") Integer productId);

    CartResponseDto removeProductFromCart(@Min(value = 1, message = "productId должен быть >= 1") Integer productId);
}
