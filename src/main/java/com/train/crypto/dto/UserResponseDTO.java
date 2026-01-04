package com.train.crypto.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long value;
}
