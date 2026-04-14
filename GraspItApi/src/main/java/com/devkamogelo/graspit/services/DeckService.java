package com.devkamogelo.graspit.services;

import com.devkamogelo.graspit.dto.DeckRequest;
import com.devkamogelo.graspit.exceptions.ResourceNotFoundException;
import com.devkamogelo.graspit.models.Deck;
import com.devkamogelo.graspit.models.User;
import com.devkamogelo.graspit.repository.DeckRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.access.AccessDeniedException;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DeckService {
    private final DeckRepository deckRepository;

    private User getAuthenticatedUser(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public List<Deck> getDecksByUser(){
        return deckRepository.findDeckByUser_Id(getAuthenticatedUser().getId());
    }

    public Deck getDeckById(UUID id){
        Deck deck = deckRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Deck not found.")
        );
        if (!getAuthenticatedUser().getId().equals(deck.getUser().getId())){
            throw new AccessDeniedException("You are not authorized to view this deck.");
        }
        return deck;
    }

    public Deck createDeck(DeckRequest request){
        Deck deck = new Deck();
        deck.setDescription(request.getDescription());
        deck.setName(request.getName());
        deck.setUser(getAuthenticatedUser());
        return deckRepository.save(deck);
    }

    public Deck updateDeck(UUID id, DeckRequest request){
        Deck deck = getDeckById(id);
        deck.setDescription(request.getDescription());
        deck.setName(request.getName());
        return deckRepository.save(deck);
    }

    public void deleteDeck(UUID id){
        Deck deck = getDeckById(id);
        deckRepository.delete(deck);
    }

}
