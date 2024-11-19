package com.ecole221.gestion_scoaire.exception;

import lombok.Data;
import lombok.Setter;

@Setter
@Data
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

}
