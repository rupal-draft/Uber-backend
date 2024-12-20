package com.project.uber.Uber.advices;

import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.NOT_FOUND)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerErrorException(Exception exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(RuntimeConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntimeConflictException(RuntimeConflictException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setMessage(exception.getLocalizedMessage())
                .setStatus(HttpStatus.CONFLICT)
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<String> errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setMessage("Input validation failed!")
                .setSubErrors(errors)
                .build();

        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
