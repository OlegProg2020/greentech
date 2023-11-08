package org.greentech.backend.service.security;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.greentech.backend.dto.request.AccountCredentialsRequestDto;
import org.greentech.backend.dto.request.AccountSignUpRequestDto;
import org.greentech.backend.dto.response.AccountWithTokenResponseDto;
import org.greentech.backend.entity.Account;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AccountSecurityManager {

    /**
     * Данный метод предназначен для использования в {@link org.greentech.backend.config.UserAuthProvider}
     * для создания {@link UsernamePasswordAuthenticationToken}.
     *
     * @apiNote В отличие от других методов, возвращает сущность {@link Account} вместо её DTO, чтобы
     * использовать сущность в качестве AuthenticationPrincipal.
     */
    Account findByPhone(@Pattern(regexp = "^(?:\\+7|8)[0-9]{10}$",
            message = "Телефон должен начинаться с 8 или +7 и продолжаться 10-ю цифрами") String phone);

    AccountWithTokenResponseDto register(@Valid AccountSignUpRequestDto signUpDto);

    AccountWithTokenResponseDto login(@Valid AccountCredentialsRequestDto credentialsDto);
}
