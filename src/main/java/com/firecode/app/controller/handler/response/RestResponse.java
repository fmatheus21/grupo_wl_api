package com.firecode.app.controller.handler.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

public class RestResponse {

    @Getter
    @Setter
    private final LocalDateTime timestamp = LocalDateTime.now();
    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private String error;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String path;

    public RestResponse() {
    }

    public RestResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static RestResponseBuilder builder() {
        return new RestResponseBuilder();
    }

}
