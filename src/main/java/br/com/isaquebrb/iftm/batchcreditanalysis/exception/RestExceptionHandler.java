package br.com.isaquebrb.iftm.batchcreditanalysis.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.isaquebrb.iftm.batchcreditanalysis.exception.ErrorMessage.*;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        StandardError body = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errorList)
                .message(ex.getMessage()).build();

        return handleExceptionInternal(ex, body, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> handleBusinessErrors(RuntimeException ex, WebRequest request) {
        StandardError body = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(Collections.singletonList(BUSINESS_ERROR.getMessage()))
                .message(ex.getMessage()).build();

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = DatabaseException.class)
    protected ResponseEntity<Object> handleDatabaseErrors(RuntimeException ex, WebRequest request) {
        StandardError body = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(Collections.singletonList(DATABASE_ERROR.getMessage()))
                .message(ex.getMessage()).build();

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = SystemException.class)
    protected ResponseEntity<Object> handleSystemErrors(RuntimeException ex, WebRequest request) {
        StandardError body = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(Collections.singletonList(SYSTEM_ERROR.getMessage()))
                .message(ex.getMessage()).build();

        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
