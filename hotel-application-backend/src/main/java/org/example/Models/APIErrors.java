package org.example.Models;

import org.springframework.http.HttpStatus;

public class APIErrors {
    private HttpStatus status;
    private String message;

    public APIErrors(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public APIErrors() {
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
}
