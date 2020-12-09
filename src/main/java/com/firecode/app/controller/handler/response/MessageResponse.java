package com.firecode.app.controller.handler.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDateTime;
import lombok.Getter;

@JsonPropertyOrder({"timestamp", "statusCode", "statusDescription", "message", "cause"})
public class MessageResponse {

    @Getter
    private final LocalDateTime timestamp = LocalDateTime.now();
    @Getter
    @JsonProperty("status_code")
    private int statusCode;
    @Getter
    @JsonProperty("status_description")
    private final String statusDescription;
    @Getter
    private final String message;
    @Getter
    private final String cause;

    public MessageResponse(int statusCode, String statusDescription, String message, String cause) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.message = message;
        this.cause = cause;
    }

}
