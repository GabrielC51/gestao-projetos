package com.gestaoprojetos.gestaoprojetos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
    private String message;
    private String timestamp;
    private String path;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = java.time.Instant.now().toString();
    }

    public ErrorResponse(String message, String path) {
        this.message = message;
        this.path = path;
        this.timestamp = java.time.Instant.now().toString();
    }
}
