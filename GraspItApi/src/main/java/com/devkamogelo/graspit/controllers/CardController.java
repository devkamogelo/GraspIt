package com.devkamogelo.graspit.controllers;

import com.devkamogelo.graspit.dto.CardRequest;
import com.devkamogelo.graspit.dto.CardResponse;
import com.devkamogelo.graspit.models.Card;
import com.devkamogelo.graspit.services.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/decks/{deckId}/cards")
public class CardController {
    private final CardService cardService;

    private CardResponse toResponse(Card card){
        CardResponse response = new CardResponse();
        response.setId(card.getId());
        response.setQuestion(card.getQuestion());
        response.setAnswer(card.getAnswer());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> getAllCards(@PathVariable UUID deckId){
        List<CardResponse> responses = cardService.getCardsByDeck(deckId)
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponse> getCardById(@PathVariable UUID deckId, @PathVariable UUID cardId){
        return ResponseEntity.ok(toResponse(cardService.getCardById(cardId, deckId)));
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@PathVariable UUID deckId, @RequestBody @Valid CardRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(cardService.createNewCard(deckId, request)));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<CardResponse> updateCard(@PathVariable UUID deckId, @PathVariable UUID cardId, @RequestBody @Valid CardRequest request){
        return ResponseEntity.ok(toResponse(cardService.updateCard(cardId, deckId, request)));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID deckId, @PathVariable UUID cardId){
        cardService.deleteCard(cardId, deckId);
        return ResponseEntity.noContent().build();
    }
}
