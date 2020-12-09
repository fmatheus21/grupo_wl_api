package com.firecode.app.controller.rule;

import com.firecode.app.controller.handler.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class MessageResponseRule {

    @Autowired
    private MessageSource messageSource;

    public MessageResponse messageResponse(int statusCode, HttpStatus statusDescription, String messageProperties, String cause) {
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new MessageResponse(statusCode, statusDescription.name(), message, cause);
    }

    public ResponseStatusException responseStatusException(HttpStatus status, String messageProperties) {
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new ResponseStatusException(status, message);
    }

}
