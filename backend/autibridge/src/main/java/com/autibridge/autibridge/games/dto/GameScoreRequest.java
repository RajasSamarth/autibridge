package com.autibridge.autibridge.games.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GameScoreRequest {
    @NotNull private Long childId;
    @NotNull private Long gameId;
    @NotNull private Integer score;
}