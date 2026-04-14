package com.devkamogelo.graspit.dto;

import lombok.Data;


import java.util.UUID;

@Data
public class CardResponse {
    private UUID id;
    private String question;
    private String answer;
}
