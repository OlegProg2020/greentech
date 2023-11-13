package org.greentech.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.data.repository.ProductRepository;
import org.greentech.backend.dto.request.ProductRequestDto;
import org.greentech.backend.dto.response.ProductResponseDto;
import org.greentech.backend.entity.Product;
import org.greentech.backend.exception.DataConflictException;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.service.ProductService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ProductServiceImpl implements ProductService {
    private static final String PRODUCT_ID_NOT_FOUND = "Товар с переданным id не существует";
    private static final String PRODUCT_HAS_RELATED_ENTITIES = "Товар привязан к заказу, добавлен в корзину или имеет связанные изображения";

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDto create(ProductRequestDto requestDto) {
        Product productToSave = requestDto.toEntity();

        return ProductResponseDto.fromEntity(
                productRepository.save(productToSave));
    }

    @Override
    public ProductResponseDto findById(Integer id) {
        return ProductResponseDto.fromEntity(
                findByIdInternal(id));
    }

    @Override
    public List<ProductResponseDto> findAllByArticle(String article) {
        return productRepository.findAllByArticle(article)
                .stream().map(ProductResponseDto::fromEntity)
                .toList();
    }

    @Override
    public List<ProductResponseDto> search(@NonNull String approximateName,
                                           @NonNull String approximateArticle,
                                           int pageSize, int pageNumber) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id").ascending());

        return productRepository.findByNameContainsIgnoreCaseAndArticleContainsIgnoreCase(
                        approximateName, approximateArticle, pageRequest)
                .stream().map(ProductResponseDto::fromEntity)
                .toList();
    }

    /**
     * Обновляет все поля, кроме характеристик товара.
     */
    @Override
    @Transactional
    public ProductResponseDto update(Integer id, ProductRequestDto requestDto) {
        Product productToUpdate = findByIdInternal(id);
        Product patch = requestDto.toEntity();

        productToUpdate.setArticle(patch.getArticle());
        productToUpdate.setName(patch.getName());
        productToUpdate.setPrice(patch.getPrice());
        productToUpdate.setDescription(patch.getDescription());

        return ProductResponseDto.fromEntity(
                productRepository.save(productToUpdate));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try {
            productRepository.deleteById(id);
            productRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException(PRODUCT_HAS_RELATED_ENTITIES);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }


    private Product findByIdInternal(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new DataMissingException(PRODUCT_ID_NOT_FOUND));
    }
}
