package com.stacksimplify.restservices.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomErrorDetails {
    private LocalDateTime timeStamp;
    private String message;
    private String errorDetails;
}
