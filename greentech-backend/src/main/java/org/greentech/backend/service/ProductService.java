package org.greentech.backend.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.greentech.backend.dto.request.ProductRequestDto;
import org.greentech.backend.dto.response.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto create(@Valid ProductRequestDto requestDto);

    ProductResponseDto findById(@Min(value = 1, message = "id должен быть >= 1") Integer id);

    List<ProductResponseDto> search(String approximateName,
                                    @Min(value = 1, message = "pageSize должен быть >= 1") int pageSize,
                                    @Min(value = 0, message = "pageNumber должен быть >= 0") int pageNumber);

    /**
     * Обновляет все поля, кроме характеристик товара.
     */
    ProductResponseDto update(@Min(value = 1, message = "id должен быть >= 1") Integer id,
                              @Valid ProductRequestDto requestDto);

    void deleteById(@Min(value = 1, message = "id должен быть >= 1") Integer id);
}
