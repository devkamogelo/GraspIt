package com.devkamogelo.graspit.repository;

import com.devkamogelo.graspit.models.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeckRepository extends JpaRepository<Deck, UUID> {
    List<Deck> findDeckByUser_Id(UUID userId);
}
