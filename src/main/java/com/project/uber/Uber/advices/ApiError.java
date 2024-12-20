package com.project.uber.Uber.advices;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> subErrors;

    public ApiError() {
    }

    public ApiError(HttpStatus status, String message, List<String> subErrors) {
        this.status = status;
        this.message = message;
        this.subErrors = subErrors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<String> subErrors) {
        this.subErrors = subErrors;
    }

    private ApiError(ApiErrorBuilder builder){
        this.status = builder.status;
        this.message = builder.message;
        this.subErrors = builder.subErrors;
    }

    public static class ApiErrorBuilder{

        private HttpStatus status;
        private String message;
        private List<String> subErrors;

        public ApiErrorBuilder setStatus(HttpStatus status){
            this.status = status;
            return this;
        }

        public ApiErrorBuilder setMessage(String message){
            this.message = message;
            return this;
        }

        public ApiErrorBuilder setSubErrors(List<String> subErrors){
            this.subErrors = subErrors;
            return this;
        }

        public ApiError build(){
            return new ApiError(this);
        }
    }
}
