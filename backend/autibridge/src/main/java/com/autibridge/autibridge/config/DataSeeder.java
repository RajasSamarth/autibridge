package com.autibridge.autibridge.config;

import com.autibridge.autibridge.games.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final GameRepository gameRepository;

    @Override
    public void run(String... args) {
        if (gameRepository.count() > 0) return; // don't seed twice

        gameRepository.save(makeGame("Emotion Match",
                "emotions",
                "Match the face to the correct emotion label. Helps children recognize basic feelings.",
                Difficulty.EASY));

        gameRepository.save(makeGame("How Are You Feeling?",
                "emotions",
                "A child picks an emotion card that describes how they feel right now.",
                Difficulty.EASY));

        gameRepository.save(makeGame("Social Story Builder",
                "social",
                "Arrange pictures to build a simple social story about everyday situations.",
                Difficulty.MEDIUM));

        gameRepository.save(makeGame("Turn Taking Game",
                "social",
                "Practice waiting and taking turns through a simple interactive activity.",
                Difficulty.EASY));

        gameRepository.save(makeGame("Word Picture Match",
                "communication",
                "Match spoken words to the correct picture. Builds vocabulary and communication.",
                Difficulty.EASY));

        gameRepository.save(makeGame("Sentence Builder",
                "communication",
                "Drag and drop words to form a simple sentence. Improves expressive language.",
                Difficulty.MEDIUM));

        gameRepository.save(makeGame("Daily Routine Sorter",
                "daily-skills",
                "Sort pictures of daily activities in the correct order — morning to bedtime.",
                Difficulty.MEDIUM));

        gameRepository.save(makeGame("Calm Down Corner",
                "emotional-regulation",
                "Interactive breathing and calm-down exercises for moments of overwhelm.",
                Difficulty.EASY));

        System.out.println("✅ Games seeded successfully");
    }

    private Game makeGame(String name, String category, String desc, Difficulty diff) {
        Game g = new Game();
        g.setName(name);
        g.setCategory(category);
        g.setDescription(desc);
        g.setDifficulty(diff);
        return g;
    }
}