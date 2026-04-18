package com.retail.retailmanagement.customException;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String field, String username) {
        super(field + " already exists: " + username);
    }
}
