package org.greentech.backend.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.greentech.backend.dto.request.ProductCharacteristicPutDto;

public interface ProductCharacteristicService {

    /**
     * Добавляет параметр и его значение в продукт или обновляет существующее значение параметра.
     */
    void putCharacteristicToProduct(@Min(value = 1, message = "id должен быть >= 1") Integer productId,
                                    @Min(value = 1, message = "id должен быть >= 1") Integer parameterId,
                                    @Valid ProductCharacteristicPutDto characteristic);

    void removeCharacteristicFromProduct(@Min(value = 1, message = "id должен быть >= 1") Integer productId,
                                         @Min(value = 1, message = "id должен быть >= 1") Integer parameterId);
}
