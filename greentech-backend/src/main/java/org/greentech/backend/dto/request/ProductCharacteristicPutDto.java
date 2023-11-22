package org.greentech.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class ProductCharacteristicPutDto {
    @NotNull(message = "Значение параметра обязательно")
    private String value;
}
