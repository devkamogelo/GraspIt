package com.devkamogelo.graspit.exceptions;

import com.devkamogelo.graspit.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;


@RestControllerAdvice
public class ExceptionHandlerService {

    private ErrorResponse buildError(int statusCode, Exception ex){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getMessage());
        response.setStatusCode(statusCode);
        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(404).body(buildError(404, ex));
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UsernameNotFoundException ex){
        return ResponseEntity.status(404).body(buildError(404, ex));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex){
        return ResponseEntity.status(403).body(buildError(403, ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValid(MethodArgumentNotValidException ex){
        ErrorResponse response = new ErrorResponse();
        response.setMessage(ex.getBindingResult().getFieldErrors().getFirst().getDefaultMessage());
        response.setStatusCode(400);
        return ResponseEntity.status(400).body(response);
    }
}
