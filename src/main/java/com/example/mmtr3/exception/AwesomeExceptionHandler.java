package com.example.mmtr3.exception;

import com.example.mmtr3.exception.MyException.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AwesomeExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ValidationErrorResponse ConstraintValidationException(ConstraintViolationException e){
        final List<Violation> violations = e.getConstraintViolations().stream()
                .map(constraintViolation ->
                        new Violation(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected ValidationErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException e){
        final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        new Violation(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violations);
    }

    @ExceptionHandler(WordRuleException.class)
    protected ResponseEntity<AwesomeException> WordRuleException(){
        return new ResponseEntity<>(new AwesomeException("Не соответствует правилам ввода"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DictionaryIsEmptyException.class)
    protected ResponseEntity<AwesomeException> DictionaryIsEmptyException(){
        return new ResponseEntity<>(new AwesomeException("Словарь пустой."),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DictionaryNotFoundException.class)
    protected ResponseEntity<AwesomeException> DictionaryNotFoundException(){
        return new ResponseEntity<>(new AwesomeException("Словарь не был найден"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WordNotFoundException.class)
    protected ResponseEntity<AwesomeException> WordNotFoundException(){
        return new ResponseEntity<>(new AwesomeException("Слово не было найдено"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TranslationNotFoundException.class)
    protected ResponseEntity<AwesomeException> TranslationNotFoundException(){
        return new ResponseEntity<>(new AwesomeException("Перевод не найден"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DictionaryAlreadyExistException.class)
    protected ResponseEntity<AwesomeException> DictionaryAlreadyExistException(){
        return new ResponseEntity<>(new AwesomeException("Словарь уже существует"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WordAlreadyExistException.class)
    protected ResponseEntity<AwesomeException> WordAlreadyExistException(){
        return new ResponseEntity<>(new AwesomeException("Слово уже существует"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TranslationAlreadyExistException.class)
    protected ResponseEntity<AwesomeException> TranslationAlreadyExistException(){
        return new ResponseEntity<>(new AwesomeException("Перевод уже существует"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<AwesomeException> MethodArgumentTypeMismatchException(){
        return new ResponseEntity<>(new AwesomeException("Введенные данные выходят за пределы ожидаемого диапазона"), HttpStatus.BAD_REQUEST);
    }



    @Getter
    @RequiredArgsConstructor
    private static class ValidationErrorResponse {
        private final List<Violation> violations;
    }

    @Getter
    @RequiredArgsConstructor
    private static class Violation{
        private final String fieldName;
        private final String message;
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException{
        private final String message;
    }
}
