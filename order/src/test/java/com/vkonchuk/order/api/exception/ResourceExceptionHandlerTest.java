package com.vkonchuk.order.api.exception;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResourceExceptionHandlerTest {

    private static final String TEST_EXCEPTION_MESSAGE = "Test exception";
    private static final String STATUS_MESSAGE_KEY = "statusMessage";

    private ResourceExceptionHandler exceptionHandler = new ResourceExceptionHandler();

    @Test
    public void exceptionHandlerHandlesInvalidOrderRequestExceptionWith400StatusCodeTest() {
        ResponseEntity<Map<String, String>> responseEntity = exceptionHandler
                .handleIncorrectRequestArguments(new InvalidOrderRequestException(TEST_EXCEPTION_MESSAGE));
        assertThat(responseEntity.getStatusCode()).as("Status code must be 400 Bad Request when Invalid Order Request Exception occurs during execution")
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody().get(STATUS_MESSAGE_KEY))
                .as("Status message must be the same as message in Invalid Order Request Exception that occurs during execution")
                .isEqualTo(TEST_EXCEPTION_MESSAGE);
    }

    @Test
    public void exceptionHandlerHandlesRuntimeExceptionWith500StatusCodeTest() {
        ResponseEntity<Map<String, String>> responseEntity = exceptionHandler.handleAllPossibleExceptions(new RuntimeException(TEST_EXCEPTION_MESSAGE));
        assertThat(responseEntity.getStatusCode()).as("Status code must be 500 Internal Server Error when Runtime Exception occurs during execution")
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody().get(STATUS_MESSAGE_KEY))
                .as("Status message must be the same as message in Runtime Exception that occurs during execution").isEqualTo(TEST_EXCEPTION_MESSAGE);
    }

}
