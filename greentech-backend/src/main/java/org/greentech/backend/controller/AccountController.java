package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Получение аккаунта по id",
            description = "Позволяет найти существующий аккаунт по id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "accountId < 1",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователь пытается получить доступ к чужому аккаунту, но " +
                                    "у него нет роли ADMIN",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Аккаунта с указанным id не существует",
                            content = @Content
                    )
            }
    )
    @GetMapping("/accounts/{accountId}")
    @PreAuthorize("#accountId.equals(authentication.principal.getId()) " +
            "or hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable(name = "accountId") Integer accountId) {
        return ResponseEntity
                .ok(accountService.findById(accountId));
    }

    @Operation(
            summary = "Получение аккаунта по номеру телефона",
            description = "Позволяет найти существующий аккаунт по номеру телефона",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "phone невалиден",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователь пытается получить доступ к чужому аккаунту, но " +
                                    "у него нет роли ADMIN",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Аккаунт с указанным телефоном не существует",
                            content = @Content
                    )
            }
    )
    @GetMapping("/accounts/byPhone/{phone}")
    @PreAuthorize("#phone.equals(authentication.principal.getPhone()) " +
            "or hasRole('ADMIN')")
    public ResponseEntity<AccountResponseDto> getAccountByPhone(@PathVariable(name = "phone") String phone) {
        return ResponseEntity
                .ok(accountService.findByPhone(phone));
    }
}
