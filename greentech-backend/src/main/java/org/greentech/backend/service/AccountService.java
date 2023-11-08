package org.greentech.backend.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.greentech.backend.dto.response.AccountResponseDto;

public interface AccountService {

    AccountResponseDto findById(@Min(value = 1, message = "id должен быть >= 1") Integer id);

    AccountResponseDto findByPhone(@Pattern(regexp = "^(?:\\+7|8)[0-9]{10}$",
            message = "Телефон должен начинаться с 8 или +7 и продолжаться 10-ю цифрами") String phone);
}
