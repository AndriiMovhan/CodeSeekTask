package com.example.codeseektask.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.*;

/**
 * This class is designed for exceptions handling
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final HttpHeaders NO_HEADERS = new HttpHeaders();

    /**
     * This method is for handling EntityNotFoundException
     *
     * @param ex      it's EntityNotFoundException
     * @param request URL request which user call
     * @return result from handleInternal method, and result it's a message which you provide in the exception
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex,
                                                           WebRequest request) {
        return handleInternal(ex, ex.getMessage(), NOT_FOUND, request);
    }

    /**
     * This method is for handling IllegalStateException
     *
     * @param ex      it's IllegalStateException
     * @param request URL request which user call
     * @return result from handleInternal method, and result it's a message which you provide in the exception
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException ex,
                                                         WebRequest request) {
        return handleInternal(ex, ex.getMessage(), UNPROCESSABLE_ENTITY, request);
    }


    /**
     * This method is for handling RuntimeException
     *
     * @param ex      it's RuntimeException
     * @param request URL request which user call
     * @return result from handleInternal method, and result it's a message which you provide in the exception
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex,
                                                    WebRequest request) {
        return handleInternal(ex, ex.getMessage(), BAD_REQUEST, request);
    }

    /**
     * This method is for handling DataIntegrityViolationException
     *
     * @param ex      it's DataIntegrityViolationException
     * @param request URL request which user call
     * @return result from handleInternal method, and result it's a message which you provide in the exception
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                   WebRequest request) {
        return handleInternal(ex, ex.getMessage(), BAD_REQUEST, request);
    }

    /**
     * This method is for handling all exceptions
     *
     * @param ex      it's exception which you provide
     * @param body    the body for the response
     * @param status  http status
     * @param request URL request which user call
     * @return ResponseEntity<>(body, headers, status),
     * where body it's the body for the response,
     * headers it's the headers for the response,
     * and status it's the response status
     */
    protected ResponseEntity<Object> handleInternal(Exception ex,
                                                    @Nullable Object body,
                                                    HttpStatus status,
                                                    WebRequest request) {
        String message = ex.getMessage();
        if (log.isDebugEnabled())
            log.debug("Api error occurred path={}, exception={}",
                    ((ServletWebRequest) request).getRequest().getRequestURI(), message, ex);
        return super.handleExceptionInternal(ex, body, NO_HEADERS, status, request);
    }
}
