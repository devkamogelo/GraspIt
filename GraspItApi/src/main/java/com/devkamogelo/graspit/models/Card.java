package com.devkamogelo.graspit.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="deck_id")
    private Deck deck;

    @Column
    private String question;

    @Column
    private String answer;
}
