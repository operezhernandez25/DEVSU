package com.devsu.op.crm.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.devsu.op.crm.exceptions.ClienteNotFoundException;
import com.devsu.op.crm.exceptions.CuentaNotFoundException;
import com.devsu.op.crm.exceptions.IdentificacionExistenteException;
import com.devsu.op.crm.exceptions.IdentificacionNoEncontradaException;
import com.devsu.op.crm.exceptions.NumeroCuentaExistenteException;
import com.devsu.op.crm.exceptions.SaldoInsuficienteException;


@RestControllerAdvice
public class ErrorHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }

     @ExceptionHandler(IdentificacionExistenteException.class)
    public ResponseEntity<String> handleIdentificacionExistente(IdentificacionExistenteException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

     @ExceptionHandler(IdentificacionNoEncontradaException.class)
    public ResponseEntity<String> handleIdentificacionNoEncontrada(IdentificacionNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

     @ExceptionHandler(CuentaNotFoundException.class)
    public ResponseEntity<String> handleCuentaNotFoundException(CuentaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NumeroCuentaExistenteException.class)
    ResponseEntity<String> numeroCuentaExistenteHandler(NumeroCuentaExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(SaldoInsuficienteException.class)
    ResponseEntity<String> SaldoInsuficienteExistenteHandler(SaldoInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
