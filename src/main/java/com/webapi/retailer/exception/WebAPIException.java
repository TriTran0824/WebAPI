package com.webapi.retailer.exception;

public class WebAPIException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public WebAPIException(String message) {
        super(message);
    }
}
