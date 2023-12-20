package org.greentech.backend.dto.response;

import lombok.*;
import org.greentech.backend.entity.Image;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
public class ImageResponseDto {
    private Integer id;
    private String uri;

    public static ImageResponseDto fromEntity(Image entity) {
        return ImageResponseDto.builder()
                .withId(entity.getId())
                .withUri(entity.getUri())
                .build();
    }
}
