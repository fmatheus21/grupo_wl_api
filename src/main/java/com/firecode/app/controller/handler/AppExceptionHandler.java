package com.firecode.app.controller.handler;

import com.firecode.app.controller.handler.response.MessageResponse;
import com.firecode.app.controller.handler.response.RestResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = messageSource.getMessage("message.error.not.readable", null, LocaleContextHolder.getLocale());
        String cause = ex.getCause().toString();
        List<MessageResponse> erros = Arrays.asList(new MessageResponse(400, HttpStatus.BAD_REQUEST.name(), message, cause));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<MessageResponse> erros = this.createListErros(400, HttpStatus.BAD_REQUEST.name(), ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleStatusException(ResponseStatusException ex, WebRequest request) {
        return RestResponse.builder()
                .exception(ex)
                .path(request.getDescription(false).substring(4))
                .entity();
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> handleEmptyResultDataAccessException(DataIntegrityViolationException ex, WebRequest request) {
        String message = messageSource.getMessage("message.error.bad.request", null, LocaleContextHolder.getLocale());
        String cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Arrays.asList(new MessageResponse(400, HttpStatus.BAD_REQUEST.name(), message, cause));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String message = messageSource.getMessage("message.error.not.found", null, LocaleContextHolder.getLocale());
        String cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Arrays.asList(new MessageResponse(404, HttpStatus.BAD_REQUEST.name(), message, cause));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<?> handlerUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        String message = messageSource.getMessage("message.error.login", null, LocaleContextHolder.getLocale());
        String cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Arrays.asList(new MessageResponse(404, HttpStatus.BAD_REQUEST.name(), message, cause));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private List<MessageResponse> createListErros(int status, String error, BindingResult result) {
        List<MessageResponse> erros = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String cause = fieldError.toString();
            erros.add(new MessageResponse(status, error, message, cause));
        });
        return erros;
    }

}
