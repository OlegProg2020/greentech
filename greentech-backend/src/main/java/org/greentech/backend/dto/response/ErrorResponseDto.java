package org.greentech.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class ErrorResponseDto {

    private String message;
}
