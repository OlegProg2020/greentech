package org.greentech.backend.entity.exception;

/*
Возникает в случае, если пользователь найден в базе данных, но указан неверный пароль
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
