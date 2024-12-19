package com.examplealpha07.bestioles.DTO;

import java.time.LocalDateTime;

public class ErrorDto {
    private LocalDateTime timestamp;
    private String message;
    private String url;

    public ErrorDto(){}

    public ErrorDto(LocalDateTime timestamp, String message, String url) {
        this.timestamp = timestamp;
        this.message = message;
        this.url = url;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
