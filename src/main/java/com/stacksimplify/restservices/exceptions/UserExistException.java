package com.stacksimplify.restservices.exceptions;

public class UserExistException extends Exception {
    public static final String USER_EXIST_EXCEPTION = "User already exists";

    public UserExistException(String message) {
        super(message);
    }
}
