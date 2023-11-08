package org.greentech.backend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Data
public class AccountCredentialsRequestDto {
    @Pattern(regexp = "^(?:\\+7|8)[0-9]{10}$", message = "Телефон должен начинаться с 8 или +7 и продолжаться 10-ю цифрами")
    private String phone;

    @NotEmpty(message = "Пароль не должен быть пустым")
    private char[] password;
}
