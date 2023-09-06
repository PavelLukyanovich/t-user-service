package ru.clevertec.userservice.exception.appException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExpireDateException extends RuntimeException {
    private String message;
}
