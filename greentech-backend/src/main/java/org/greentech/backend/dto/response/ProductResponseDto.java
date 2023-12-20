package org.greentech.backend.dto.response;

import lombok.*;
import org.greentech.backend.entity.Parameter;
import org.greentech.backend.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
public class ProductResponseDto {
    private Integer id;
    private String article;
    private String name;
    private BigDecimal price;
    private String description;
    private Map<ParameterResponseDto, String> characteristics = new HashMap<>();
    private List<ImageResponseDto> images = new ArrayList<>();

    public static ProductResponseDto fromEntity(Product entity) {
        // Преобразуем Map<Parameter, String> в Map<ParameterResponseDto, String>
        Map<ParameterResponseDto, String> mappedCharacteristics = new HashMap<>();
        for (Map.Entry<Parameter, String> characteristic : entity.getCharacteristics().entrySet()) {
            mappedCharacteristics.put(
                    ParameterResponseDto.fromEntity(characteristic.getKey()),
                    characteristic.getValue()
            );
        }

        return ProductResponseDto.builder()
                .withId(entity.getId())
                .withArticle(entity.getArticle())
                .withName(entity.getName())
                .withPrice(entity.getPrice())
                .withDescription(entity.getDescription())
                .withCharacteristics(mappedCharacteristics)
                .withImages(entity.getImages().stream().map(ImageResponseDto::fromEntity).toList())
                .build();
    }
}
