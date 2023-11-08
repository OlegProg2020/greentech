package org.greentech.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.data.repository.AccountRepository;
import org.greentech.backend.dto.response.AccountResponseDto;
import org.greentech.backend.entity.Account;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Validated
public class AccountServiceImpl implements AccountService {
    private static final String ACCOUNT_ID_NOT_FOUND = "Аккаунта с таким id не существует";
    private static final String ACCOUNT_PHONE_NOT_FOUND = "Аккаунта с таким телефоном не существует";

    private final AccountRepository accountRepository;


    @Override
    public AccountResponseDto findById(Integer id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new DataMissingException(ACCOUNT_ID_NOT_FOUND));
        return AccountResponseDto.fromEntity(account);
    }

    @Override
    public AccountResponseDto findByPhone(String phone) {
        Account account = accountRepository.findByPhone(phone)
                .orElseThrow(() -> new DataMissingException(ACCOUNT_PHONE_NOT_FOUND));
        return AccountResponseDto.fromEntity(account);
    }
}
