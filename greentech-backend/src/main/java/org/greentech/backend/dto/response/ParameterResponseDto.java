package org.greentech.backend.dto.response;

import lombok.*;
import org.greentech.backend.entity.Parameter;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
public class ParameterResponseDto {
    private Integer id;
    private String name;

    public static ParameterResponseDto fromEntity(Parameter entity) {
        return ParameterResponseDto.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .build();
    }
}
