package com.autibridge.autibridge.games;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;      // "emotions", "social", "communication"
    private String description;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
}