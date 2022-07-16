package com.shivam.bankapp.exception;


import com.shivam.bankapp.dto.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler({InvalidAmountException.class,AdminPermitException.class, AccountNotFoundException.class,AccountBlockedException.class,AccountNotActiveException.class})
    public ResponseEntity<AppResponse> appExceptionHandler(Exception ex) {
        AppResponse response = new AppResponse();
        response.setMsg(ex.getMessage());
        response.setSts("fail");
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> validationErrorHandler(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(err -> {
            String fld = ((FieldError) err).getField();
            String errMsg = err.getDefaultMessage();
            errors.put(fld, errMsg);
        });

        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}
