package org.greentech.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.data.repository.CartRepository;
import org.greentech.backend.data.repository.ProductRepository;
import org.greentech.backend.dto.response.CartResponseDto;
import org.greentech.backend.entity.Account;
import org.greentech.backend.entity.Cart;
import org.greentech.backend.entity.Product;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.service.CartService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public CartResponseDto addProductToCart(Integer productId) {
        Cart cart = getCurrentUserCart();
        Product product = findProductByIdInternal(productId);

        cart.getProducts().add(product);

        return CartResponseDto.fromEntity(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartResponseDto removeProductFromCart(Integer productId) {
        Cart cart = getCurrentUserCart();
        Product product = findProductByIdInternal(productId);

        cart.getProducts().remove(product);

        return CartResponseDto.fromEntity(cartRepository.save(cart));
    }


    private Product findProductByIdInternal(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataMissingException("Продукт не найден"));
    }

    private Cart getCurrentUserCart() {
        Account currentUser = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser.getCart();
    }
}
