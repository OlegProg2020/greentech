package org.greentech.backend.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public interface ProductParameterService {

    /**
     * Добавляет параметр и его значение в продукт или обновляет существующее значение параметра.
     */
    void putCharacteristicToProduct(@Min(value = 1, message = "id должен быть >= 1") Integer productId,
                                    @Min(value = 1, message = "id должен быть >= 1") Integer parameterId,
                                    @NotNull(message = "Значение параметра обязательно") String value);

    void removeCharacteristicFromProduct(@Min(value = 1, message = "id должен быть >= 1") Integer productId,
                                         @Min(value = 1, message = "id должен быть >= 1") Integer parameterId);
}
