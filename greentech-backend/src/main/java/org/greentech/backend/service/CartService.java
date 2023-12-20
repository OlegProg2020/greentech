package org.greentech.backend.service;

import jakarta.validation.constraints.Min;
import org.greentech.backend.dto.response.CartResponseDto;

public interface CartService {

    CartResponseDto addProduct(@Min(value = 1, message = "cartId должен быть >= 1") Integer cartId,
                               @Min(value = 1, message = "productId должен быть >= 1") Integer productId);

    CartResponseDto removeProduct(@Min(value = 1, message = "cartId должен быть >= 1") Integer cartId,
                               @Min(value = 1, message = "productId должен быть >= 1") Integer productId);
}
