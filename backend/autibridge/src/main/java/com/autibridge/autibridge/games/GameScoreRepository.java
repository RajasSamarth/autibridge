package com.autibridge.autibridge.games;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GameScoreRepository extends JpaRepository<GameScore, Long> {
    List<GameScore> findByChildId(Long childId);
}