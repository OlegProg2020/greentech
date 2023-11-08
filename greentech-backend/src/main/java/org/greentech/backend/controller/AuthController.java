package org.greentech.backend.controller;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.request.AccountCredentialsRequestDto;
import org.greentech.backend.dto.request.AccountSignUpRequestDto;
import org.greentech.backend.dto.response.AccountWithTokenResponseDto;
import org.greentech.backend.service.security.AccountSecurityManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AuthController {
    private final AccountSecurityManager accountSecurityManager;

    @PostMapping("/register")
    public ResponseEntity<AccountWithTokenResponseDto> register(@RequestBody AccountSignUpRequestDto signUpDto) {
        AccountWithTokenResponseDto responseDto = accountSecurityManager.register(signUpDto);

        return ResponseEntity
                .created(URI.create("/accounts/" + responseDto.getId()))
                .body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountWithTokenResponseDto> login(@RequestBody AccountCredentialsRequestDto credentialsDto) {
        AccountWithTokenResponseDto responseDto = accountSecurityManager.login(credentialsDto);

        return ResponseEntity
                .ok(responseDto);
    }
}
