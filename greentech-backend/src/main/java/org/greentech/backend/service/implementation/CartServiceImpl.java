package org.greentech.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.data.repository.CartRepository;
import org.greentech.backend.data.repository.ProductRepository;
import org.greentech.backend.dto.response.CartResponseDto;
import org.greentech.backend.entity.Cart;
import org.greentech.backend.entity.Product;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CartResponseDto addProduct(Integer cartId, Integer productId) {
        Cart cart = findCartByIdInternal(cartId);
        Product product = findProductByIdInternal(productId);

        cart.getProducts().add(product);

        return CartResponseDto.fromEntity(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartResponseDto removeProduct(Integer cartId, Integer productId) {
        Cart cart = findCartByIdInternal(cartId);
        Product product = findProductByIdInternal(productId);

        cart.getProducts().remove(product);

        return CartResponseDto.fromEntity(cartRepository.save(cart));
    }

    private Cart findCartByIdInternal(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new DataMissingException("Корзина не найдена"));
    }

    private Product findProductByIdInternal(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataMissingException("Продукт не найден"));
    }
}
