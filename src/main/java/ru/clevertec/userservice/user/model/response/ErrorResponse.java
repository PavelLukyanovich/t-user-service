package ru.clevertec.userservice.user.model.response;

public record ErrorResponse<T>(T data, String message) {

}
