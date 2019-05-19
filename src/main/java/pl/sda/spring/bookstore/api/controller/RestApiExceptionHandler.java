package pl.sda.spring.bookstore.api.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestApiExceptionHandler {

    private static final String DEFAULT_EX_CODE = "EX_DEF";

    @ExceptionHandler
    @ResponseBody
    public ErrorMessage handle(Exception ex) {
        return new ErrorMessage(DEFAULT_EX_CODE, ex.getMessage());
    }
}
