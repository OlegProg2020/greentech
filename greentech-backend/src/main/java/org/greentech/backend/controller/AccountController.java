package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.response.AccountResponseDto;
import org.greentech.backend.service.AccountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/accounts/{accountId}")
    @PreAuthorize("#accountId.equals(authentication.principal.getId()) " +
            "or hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable(name = "accountId") Integer accountId) {
        return ResponseEntity
                .ok(accountService.findById(accountId));
    }

    @GetMapping("/accounts/byPhone/{phone}")
    @PreAuthorize("#phone.equals(authentication.principal.getPhone()) " +
            "or hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDto> getAccountByPhone(@PathVariable(name = "phone") String phone) {
        return ResponseEntity
                .ok(accountService.findByPhone(phone));
    }
}
