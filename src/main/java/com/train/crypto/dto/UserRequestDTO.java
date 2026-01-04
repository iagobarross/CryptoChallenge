package com.train.crypto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {
    
    
    private Long id;

    @NotBlank(message = "Documento é obrigatório")
    private String userDocument;

    @NotBlank(message = "Token é obrigatório")
    private String creditCardToken;

    @NotNull(message = "Valor é obrigatório")
    private Long value;
}
