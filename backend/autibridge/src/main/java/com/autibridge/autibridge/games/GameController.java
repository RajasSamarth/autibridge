package com.autibridge.autibridge.games;

import com.autibridge.autibridge.child.*;
import com.autibridge.autibridge.games.dto.GameScoreRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;
    private final GameScoreRepository gameScoreRepository;
    private final ChildRepository childRepository;

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameRepository.findAll());
    }

    @PostMapping("/scores")
    public ResponseEntity<GameScore> submitScore(@Valid @RequestBody GameScoreRequest req) {
        Child child = childRepository.findById(req.getChildId())
                .orElseThrow(() -> new RuntimeException("Child not found"));
        Game game = gameRepository.findById(req.getGameId())
                .orElseThrow(() -> new RuntimeException("Game not found"));

        GameScore score = new GameScore();
        score.setChild(child);
        score.setGame(game);
        score.setScore(req.getScore());
        return ResponseEntity.ok(gameScoreRepository.save(score));
    }

    @GetMapping("/scores/child/{childId}")
    public ResponseEntity<List<GameScore>> getScoresForChild(@PathVariable Long childId) {
        return ResponseEntity.ok(gameScoreRepository.findByChildId(childId));
    }
}