package org.greentech.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.greentech.backend.entity.Image;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class ImageRequestDto {

    @NotNull(message = "Не указан uri")
    private String uri;

    public Image toEntity() {
        return Image.builder()
                .withUri(this.uri)
                .build();
    }
}
