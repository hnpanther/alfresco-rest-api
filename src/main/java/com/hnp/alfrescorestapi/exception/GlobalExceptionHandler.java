package com.hnp.alfrescorestapi.exception;

import com.hnp.alfrescorestapi.dto.GenerealResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.net.ConnectException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = { MultipartException.class, MultipartException.class })
    public ResponseEntity<Object> handleMultipartException(Exception e) {
        logger.error("exception handler: " +e.getMessage(), e);

        GenerealResponse generealResponse = new GenerealResponse();
        generealResponse.setDescription("file incorrect!");
        generealResponse.setStatus(400);
        generealResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(400).body(generealResponse);
    }

    @ExceptionHandler(value = {ConnectException.class, ConnectException.class})
    public ResponseEntity<Object> handleConnectException(Exception e) {
        logger.error("exception handler: " +e.getMessage(), e);

        GenerealResponse generealResponse = new GenerealResponse();
        generealResponse.setDescription("please contact your system administrator");
        generealResponse.setStatus(503);
        generealResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(503).body(generealResponse);
    }

    @ExceptionHandler(value = {CustomForbiddenException.class, CustomForbiddenException.class})
    public ResponseEntity<Object> handleCustomForbiddenException(Exception e) {
        logger.error("exception handler: " +e.getMessage(), e);

        GenerealResponse generealResponse = new GenerealResponse();
        generealResponse.setDescription("please contact your system administrator");
        generealResponse.setStatus(403);
        generealResponse.setTimeStamp(LocalDateTime.now());

        return ResponseEntity.status(403).body(generealResponse);
    }



}
