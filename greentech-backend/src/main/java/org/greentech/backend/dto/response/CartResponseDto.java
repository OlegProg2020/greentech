package org.greentech.backend.dto.response;

import lombok.*;
import org.greentech.backend.entity.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
public class CartResponseDto {
    private Integer id;
    private List<ProductResponseDto> products = new ArrayList<>();

    public static CartResponseDto fromEntity(Cart entity) {
        return CartResponseDto.builder()
                .withId(entity.getId())
                .withProducts(entity.getProducts().stream().map(ProductResponseDto::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
