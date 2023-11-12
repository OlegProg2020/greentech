package org.greentech.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.greentech.backend.entity.Parameter;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class ParameterRequestDto {

    @NotBlank(message = "Имя параметра не может состоять только из пробелов")
    private String name;

    public Parameter toEntity() {
        return Parameter.builder()
                .withName(this.name)
                .build();
    }
}
