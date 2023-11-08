package org.greentech.backend.service;

import jakarta.validation.constraints.Min;
import org.greentech.backend.entity.Account;

public interface AccountService {

    Account findById(@Min(value = 1, message = "id должен быть >= 1") Integer id);

}
