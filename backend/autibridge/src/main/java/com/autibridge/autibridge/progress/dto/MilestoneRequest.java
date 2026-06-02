package com.autibridge.autibridge.progress.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MilestoneRequest {
    @NotNull private Long childId;
    @NotBlank private String title;
    private LocalDate achievedAt;
    private String notes;
}