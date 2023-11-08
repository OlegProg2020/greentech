package org.greentech.backend.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.greentech.backend.entity.Account;

@Data
public class AccountSignUpRequestDto {
    @Pattern(regexp = "^(?:\\+7|8)[0-9]{10}$", message = "Телефон должен начинаться с 8 или +7 и продолжаться 10-ю цифрами")
    private String phone;

    @NotBlank(message = "Имя не должно состоять только из пробелов")
    private String name;

    @Schema(type = "string")
    @NotEmpty(message = "Пароль не должен быть пустым")
    private char[] password;

    /***
     * Возвращает новый аккаунт, заполняя все поля, кроме password.
     */
    public Account toEntity() {
        return Account.builder()
                .withPhone(this.phone)
                .withName(this.name)
                .build();
    }
}
