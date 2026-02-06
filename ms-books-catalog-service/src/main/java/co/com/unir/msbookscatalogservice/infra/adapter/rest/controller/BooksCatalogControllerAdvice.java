package co.com.unir.msbookscatalogservice.infra.adapter.rest.controller;

import co.com.unir.msbookscatalogservice.domain.exception.BookNotFoundException;
import co.com.unir.msbookscatalogservice.domain.exception.EmptyBookListException;
import co.com.unir.msbookscommon.DTOs.BookDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class BooksCatalogControllerAdvice {

    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 409
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> bookNotFoundHandler(){
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(value= HttpStatus.NO_CONTENT, reason="No se encontraron items que cumplan con la especificacion")  // 409
    @ExceptionHandler(EmptyBookListException.class)
    public List<BookDTO> bookListIsEmpty(){
        return Collections.emptyList();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleBodyValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getAllErrors().isEmpty()
                ? "Validation error"
                : ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleParamValidation(ConstraintViolationException ex) {
        String msg = ex.getConstraintViolations().isEmpty()
                ? "Validation error"
                : ex.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.badRequest().body(msg);
    }

}
