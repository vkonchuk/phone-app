package com.vkonchuk.catalog.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllPossibleExceptions(Exception e) {
        logger.error("An exception occurred and it can not be handled properly:", e);
        return prepareResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Map<String, String>> prepareResponse(Exception e, HttpStatus httpStatus) {
        Map<String, String> response = new HashMap<>();
        response.put("statusMessage", e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

}
