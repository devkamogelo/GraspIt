package com.devkamogelo.graspit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeckRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
