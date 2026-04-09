package com.devkamogelo.graspit.repository;

import com.devkamogelo.graspit.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}
