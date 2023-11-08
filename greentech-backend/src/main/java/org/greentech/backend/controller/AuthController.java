package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Аутентификация", description = "Позволяет зарегистрироваться или войти в аккаунт. Место получения токена.")
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AuthController {
    private final AccountSecurityManager accountSecurityManager;

    @Operation(
            summary = "Регистрация нового аккаунта",
            description = "Позволяет создать новый аккаунт с ролью CUSTOMER",
            responses = {
                    @ApiResponse(responseCode = "201"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Тело запроса невалидно",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Аккаунт с указанным телефоном уже существует",
                            content = @Content
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AccountWithTokenResponseDto> register(@RequestBody AccountSignUpRequestDto signUpDto) {
        AccountWithTokenResponseDto responseDto = accountSecurityManager.register(signUpDto);

        return ResponseEntity
                .created(URI.create("/api/accounts/" + responseDto.getId()))
                .body(responseDto);
    }

    @Operation(
            summary = "Вход в существующий аккаунт",
            description = "Позволяет войти в существующий аккаунт",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Тело запроса невалидно",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Указан неверный пароль",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Аккаунта с указанным телефоном не существует",
                            content = @Content
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<AccountWithTokenResponseDto> login(@RequestBody AccountCredentialsRequestDto credentialsDto) {
        AccountWithTokenResponseDto responseDto = accountSecurityManager.login(credentialsDto);

        return ResponseEntity
                .ok(responseDto);
    }
}
