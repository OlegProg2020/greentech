package org.greentech.backend.exception;

/**
 * Возникает в случае, если сохраняемые или изменяемые данные нарушают ограничение
 * уникальности
 */
public class DataConflictException extends RuntimeException {

    public DataConflictException() {
    }

    public DataConflictException(String message) {
        super(message);
    }

}