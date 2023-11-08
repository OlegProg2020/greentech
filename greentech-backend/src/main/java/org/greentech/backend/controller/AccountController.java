package org.greentech.backend.controller;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.response.AccountResponseDto;
import org.greentech.backend.service.AccountService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts/{accountId}")
    public AccountResponseDto getAccountById(@PathVariable(name = "accountId") Integer accountId) {
        return accountService.findById(accountId);
    }

    @GetMapping("/accounts/{phone}")
    public AccountResponseDto getAccountById(@PathVariable(name = "phone") String phone) {
        return accountService.findByPhone(phone);
    }
}
