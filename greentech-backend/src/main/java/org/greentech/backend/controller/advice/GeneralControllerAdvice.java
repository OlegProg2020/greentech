package org.greentech.backend.controller.advice;

import jakarta.validation.ConstraintViolationException;
import org.greentech.backend.dto.response.ErrorResponseDto;
import org.greentech.backend.exception.DataConflictException;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GeneralControllerAdvice {
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<ErrorResponseDto> onValidationExceptions(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<ErrorResponseDto> onRequestNotValidExceptions(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(exception.getLocalizedMessage()));
    }

    @ExceptionHandler({
            InvalidPasswordException.class
    })
    public ResponseEntity<ErrorResponseDto> onInvalidPasswordException(InvalidPasswordException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(DataMissingException.class)
    public ResponseEntity<ErrorResponseDto> onDataMissingException(DataMissingException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<ErrorResponseDto> onDataConflictException(DataConflictException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDto(exception.getMessage()));
    }
}
