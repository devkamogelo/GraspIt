package com.devkamogelo.graspit.controllers;

import com.devkamogelo.graspit.dto.DeckRequest;
import com.devkamogelo.graspit.dto.DeckResponse;
import com.devkamogelo.graspit.models.Deck;
import com.devkamogelo.graspit.services.DeckService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/decks")
public class DeckController {

    private final DeckService deckService;

    private DeckResponse toResponse(Deck deck){
        DeckResponse response = new DeckResponse();
        response.setId(deck.getId());
        response.setDescription(deck.getDescription());
        response.setName(deck.getName());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<DeckResponse>> getAllDecks(){
        List<DeckResponse> responseList = deckService.getDecksByUser()
                .stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{deckId}")
    public ResponseEntity<DeckResponse> getDeckById(@PathVariable UUID deckId){
        return ResponseEntity.ok(toResponse(deckService.getDeckById(deckId)));
    }

    @PostMapping
    public ResponseEntity<DeckResponse> createNewDeck(@RequestBody @Valid DeckRequest request){
        Deck deck = deckService.createDeck(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(deck));
    }

    @PutMapping("/{deckId}")
    public ResponseEntity<DeckResponse> updateDeck(@RequestBody @Valid DeckRequest request, @PathVariable UUID deckId){
        return ResponseEntity.ok(toResponse(deckService.updateDeck(deckId, request)));
    }

    @DeleteMapping("/{deckId}")
    public ResponseEntity<Void> deleteDeck(@PathVariable UUID deckId){
        deckService.deleteDeck(deckId);
        return ResponseEntity.noContent().build();
    }
}
