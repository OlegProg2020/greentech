package org.greentech.backend.entity.exception;

/**
 * Возникает в случае, если запрашиваемые данные не существуют в базе данных
 */
public class DataMissingException extends RuntimeException {

    public DataMissingException() {
    }

    public DataMissingException(String message) {
        super(message);
    }

}
