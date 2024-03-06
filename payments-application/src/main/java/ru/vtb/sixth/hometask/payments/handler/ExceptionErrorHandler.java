package ru.vtb.sixth.hometask.payments.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vtb.sixth.hometask.payments.controller.ProductsController;

@ControllerAdvice(assignableTypes = {
        ProductsController.class
})
public class ExceptionErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
