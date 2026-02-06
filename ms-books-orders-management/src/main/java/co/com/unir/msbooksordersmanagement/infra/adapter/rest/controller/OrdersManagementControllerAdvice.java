package co.com.unir.msbooksordersmanagement.infra.adapter.rest.controller;

import co.com.unir.msbookscommon.DTOs.BookDTO;
import co.com.unir.msbooksordersmanagement.application.error.BooksServiceUnavailableException;
import co.com.unir.msbooksordersmanagement.application.error.ItemNotFoundException;
import co.com.unir.msbooksordersmanagement.application.error.NotEnoughStockException;
import co.com.unir.msbooksordersmanagement.domain.exception.OrderNotFoundException;
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
public class OrdersManagementControllerAdvice {

    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BooksServiceUnavailableException.class)
    public ResponseEntity<String> bookServiceNotAvailable(){
        return ResponseEntity.internalServerError().body("Ocurrio un error interno al consultar con el modulo de stock de libros, intente mas tarde");
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> orderNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND)  // 409
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> bookNotAvailable(ItemNotFoundException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST)  // 409
    @ExceptionHandler(NotEnoughStockException.class)
    public ResponseEntity<String> bookNotOnStock(NotEnoughStockException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
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
