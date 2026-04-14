package com.devkamogelo.graspit.services;


import com.devkamogelo.graspit.dto.CardRequest;
import com.devkamogelo.graspit.exceptions.ResourceNotFoundException;
import com.devkamogelo.graspit.models.Card;
import com.devkamogelo.graspit.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CardService {
    private final DeckService deckService;
    private final CardRepository cardRepository;

    public List<Card> getCardsByDeck(UUID deckId){
        return cardRepository.findCardsByDeck(deckId);
    }

    public Card getCardById(UUID cardId, UUID deckId){
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new ResourceNotFoundException("Card not found.")
        );

        if(!deckService.getDeckById(deckId).equals(card.getDeck())){
            throw new AccessDeniedException("Card does not belong to this deck");
        }
        return card;
    }

    public Card createNewCard(UUID deckId, CardRequest request){
        Card card = new Card();
        card.setQuestion(request.getQuestion());
        card.setAnswer(request.getAnswer());
        card.setDeck(deckService.getDeckById(deckId));
        return cardRepository.save(card);
    }

    public Card updateCard(UUID cardId, UUID deckId, CardRequest request){
        Card card = getCardById(cardId, deckId);
        card.setQuestion(request.getQuestion());
        card.setAnswer(request.getAnswer());
        return cardRepository.save(card);
    }

    public void deleteCard(UUID cardId, UUID deckId){
        Card card = getCardById(cardId, deckId);
        cardRepository.delete(card);
    }
}

