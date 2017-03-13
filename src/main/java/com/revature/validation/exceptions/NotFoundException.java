package com.revature.validation.exceptions;

/**
 * Exception used for when and entry is not found in the database and a 404 Not Found
 * http status code is desired
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(){}

    public NotFoundException(String message){
        super(message);
    }

}
