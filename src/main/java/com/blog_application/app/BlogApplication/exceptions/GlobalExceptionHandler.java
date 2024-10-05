package com.blog_application.app.BlogApplication.exceptions;

import com.blog_application.app.BlogApplication.utlis.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException and returns a detailed error response.
     *
     * @param exception the ResourceNotFoundException that was thrown
     * @return a ResponseEntity containing the error details and HTTP status code
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        // Prepare the details map to provide more context on the missing resource
        Map<String, String> details = new HashMap<>();
        details.put("resource", exception.getResourceName()); // Resource that was not found
        details.put("query", exception.getFieldName() + ": " + exception.getFieldValue()); // Query information

        // Prepare the error response map
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "error"); // Define the response status
        errorResponse.put("title", "Data Not Found"); // Title for the error
        errorResponse.put("message", exception.getMessage()); // Exception message from ResourceNotFoundException
        errorResponse.put("details", details); // Include the details of the missing resource

        // Format the current timestamp in ISO format
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        errorResponse.put("timestamp", LocalDateTime.now().format(formatter)); // Add timestamp to the response

        // Return the response entity with error details and a 404 NOT_FOUND status
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
