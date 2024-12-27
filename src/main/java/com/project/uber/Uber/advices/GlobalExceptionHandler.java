package com.project.uber.Uber.advices;

import com.project.uber.Uber.exceptions.ResourceNotFoundException;
import com.project.uber.Uber.exceptions.RuntimeConflictException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
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

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUsernameNotFoundException(UsernameNotFoundException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationServiceException(AuthenticationServiceException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(AuthenticationException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleJwtException(JwtException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.UNAUTHORIZED)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException exception){
        ApiError apiError = new ApiError
                .ApiErrorBuilder()
                .setStatus(HttpStatus.FORBIDDEN)
                .setMessage(exception.getLocalizedMessage())
                .build();

        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError){
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
