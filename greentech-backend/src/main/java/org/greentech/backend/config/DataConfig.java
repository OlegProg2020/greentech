package org.greentech.backend.config;

import org.greentech.backend.data.repository.AccountRepository;
import org.greentech.backend.entity.Account;
import org.greentech.backend.entity.enums.Role;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.CharBuffer;

@Configuration
public class DataConfig {

    @Bean
    public ApplicationRunner dataLoader(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Account account = Account.builder()
                    .withId(1)
                    .withName("admin")
                    .withPhone("80000000000")
                    .withPassword(passwordEncoder.encode(CharBuffer.wrap(new char[] {'0', '0', '0', '0'})))
                    .withRole(Role.ADMIN)
                    .build();
            accountRepository.save(account);
        };
    }
}
