package com.examplealpha07.bestioles.DTO;

public class ResponseDTO {
    public record GeneralResponse<T>(boolean flag, String message, T obj) {};
}
