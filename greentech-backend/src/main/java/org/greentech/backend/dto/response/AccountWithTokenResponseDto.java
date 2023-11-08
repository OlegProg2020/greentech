package org.greentech.backend.dto.response;

import lombok.*;
import org.greentech.backend.entity.Account;
import org.greentech.backend.entity.enums.Role;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with")
public class AccountWithTokenResponseDto {
    private Integer id;
    private String phone;
    private Role role;
    private String name;
    private String token;

    public static AccountWithTokenResponseDto fromEntity(Account entity) {
        return AccountWithTokenResponseDto.builder()
                .withId(entity.getId())
                .withPhone(entity.getPhone())
                .withRole(entity.getRole())
                .withName(entity.getName())
                .build();
    }
}
