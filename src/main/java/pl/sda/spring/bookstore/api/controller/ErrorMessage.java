package pl.sda.spring.bookstore.api.controller;

import lombok.Value;

@Value
public class ErrorMessage {
    private String exCode;
    private String msg;
}
