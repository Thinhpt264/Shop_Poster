package com.demo.configuration;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(value = {AccessDeniedException.class})
    public void handleAccessDeniedException(HttpServletResponse response) throws IOException {
        if (!response.isCommitted()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
        }
    }
}
