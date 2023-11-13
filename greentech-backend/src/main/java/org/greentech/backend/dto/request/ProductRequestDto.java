package org.greentech.backend.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.greentech.backend.entity.Product;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class ProductRequestDto {

    @NotBlank(message = "Имя товара не может состоять только из пробелов")
    private String name;

    @DecimalMin(value = "0.0", message = "Цена товара должна быть >= 0")
    private BigDecimal price;

    @NotNull(message = "Не указано описание товара")
    private String description;

    public Product toEntity() {
        return Product.builder()
                .withName(this.name)
                .withPrice(this.price)
                .withDescription(this.description)
                .build();
    }
}
