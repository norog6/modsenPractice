package com.modsen.practise.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ValidationErrorMessage {
    private final List<Violation> violations;
}
