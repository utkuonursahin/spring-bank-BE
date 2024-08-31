package me.utku.springbank.generic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
@AllArgsConstructor
public class GenericResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public ResponseEntity<GenericResponse<T>> toResponseEntity() {
        return ResponseEntity.status(statusCode).body(this);
    }
}
