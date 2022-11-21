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

    @ExceptionHandler(EmptyDictionaryListException.class)
    protected ResponseEntity<AwesomeException> EmptyDictionaryListException(){
        return new ResponseEntity<>(new AwesomeException("No words in the list"),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WordNotFoundException.class)
    protected ResponseEntity<AwesomeException> WordNotFoundException(){
        return new ResponseEntity<>(new AwesomeException("Word not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TranslationNotFoundException.class)
    protected ResponseEntity<AwesomeException> TranslationNotFoundException(){
        return new ResponseEntity<>(new AwesomeException("Translation not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WordAlreadyExistException.class)
    protected ResponseEntity<AwesomeException> WordAlreadyExistException(){
        return new ResponseEntity<>(new AwesomeException("Word already exist"), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(TranslationAlreadyExistException.class)
    protected ResponseEntity<AwesomeException> TranslationAlreadyExistException(){
        return new ResponseEntity<>(new AwesomeException("Translation already exist"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<AwesomeException> MethodArgumentTypeMismatchException(){
        return new ResponseEntity<>(new AwesomeException("The data entered is outside the expected range"), HttpStatus.BAD_REQUEST);
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
