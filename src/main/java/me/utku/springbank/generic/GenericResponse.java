package me.utku.springbank.generic;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * This class is a generic response class that can be used to return responses from the API.
 * ok() method is used to return a successful response with the data with the custom status code optionally.
 * error() method is used to return an error response with the error message and the status code.
 */
@Getter
@Setter
@Builder
public class GenericResponse<T> {
    private final int statusCode;
    private final T data;
    private final Map<String, String> error;

    public static <T> GenericResponse<T> ok(T data) {
        return GenericResponse.<T>builder()
                .statusCode(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> ok(int statusCode, T data) {
        return GenericResponse.<T>builder()
                .statusCode(statusCode)
                .data(data)
                .build();
    }

    public static <T> GenericResponse<T> error(int statusCode, String message) {
        return GenericResponse.<T>builder()
                .statusCode(statusCode)
                .error(Map.of("reason", message))
                .build();
    }

    public ResponseEntity<GenericResponse<T>> toResponseEntity() {
        return ResponseEntity.status(statusCode).body(this);
    }
}
