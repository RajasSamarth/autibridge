package com.autibridge.autibridge.child.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChildRequest {
    @NotBlank private String name;
    private Integer age;
    private String diagnosisLevel;
    private String notes;
}