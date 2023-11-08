package org.greentech.backend.service.security;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.data.repository.AccountRepository;
import org.greentech.backend.dto.request.AccountCredentialsRequestDto;
import org.greentech.backend.dto.request.AccountSignUpRequestDto;
import org.greentech.backend.dto.response.AccountWithTokenResponseDto;
import org.greentech.backend.entity.Account;
import org.greentech.backend.exception.DataConflictException;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.exception.InvalidPasswordException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;

@Service
@RequiredArgsConstructor
public class AccountSecurityManagerImpl implements AccountSecurityManager {
    private static final String ACCOUNT_PHONE_NOT_FOUND = "Аккаунта с таким телефоном не существует";
    private static final String ACCOUNT_WITH_SUCH_PHONE_ALREADY_EXISTS = "Аккаунт с таким телефоном уже существует";
    private static final String INVALID_PASSWORD = "Неверный пароль";

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account findByPhone(String phone) {
        return findByPhoneInternal(phone);
    }

    @Override
    @Transactional
    public AccountWithTokenResponseDto register(AccountSignUpRequestDto signUpDto) {
        Account newAccount = signUpDto.toEntity();
        newAccount.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));
        try {
            return AccountWithTokenResponseDto.fromEntity(
                    accountRepository.saveAndFlush(newAccount));
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException(ACCOUNT_WITH_SUCH_PHONE_ALREADY_EXISTS);
        }
    }

    @Override
    public AccountWithTokenResponseDto login(AccountCredentialsRequestDto credentialsDto) {
        Account account = findByPhoneInternal(credentialsDto.getPhone());

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), account.getPassword())) {
            return AccountWithTokenResponseDto.fromEntity(account);
        } else {
            throw new InvalidPasswordException(INVALID_PASSWORD);
        }
    }


    private Account findByPhoneInternal(String phone) {
        return accountRepository.findByPhone(phone)
                .orElseThrow(() -> new DataMissingException(ACCOUNT_PHONE_NOT_FOUND));
    }
}
