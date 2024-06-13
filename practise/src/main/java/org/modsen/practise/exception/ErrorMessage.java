package org.modsen.practise.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private Integer statusCode;
    private String message;
}
