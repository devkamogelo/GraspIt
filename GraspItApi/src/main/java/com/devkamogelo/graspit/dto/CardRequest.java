package com.devkamogelo.graspit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CardRequest {
    @NotBlank
    private String question;
    @NotBlank
    private String answer;
}
