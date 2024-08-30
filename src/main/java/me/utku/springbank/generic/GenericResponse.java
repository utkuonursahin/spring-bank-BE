package me.utku.springbank.generic;

import lombok.Builder;

@Builder
public class GenericResponse<T> {
    private int statusCode;
    private String message;
    private T data;
}
