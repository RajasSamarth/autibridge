package com.autibridge.autibridge.progress;

import com.autibridge.autibridge.child.*;
import com.autibridge.autibridge.progress.dto.MilestoneRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class ProgressController {

    private final MilestoneRepository milestoneRepository;
    private final ChildRepository childRepository;

    @PostMapping("/milestones")
    public ResponseEntity<Milestone> addMilestone(@Valid @RequestBody MilestoneRequest req) {
        Child child = childRepository.findById(req.getChildId())
                .orElseThrow(() -> new RuntimeException("Child not found"));

        Milestone m = new Milestone();
        m.setChild(child);
        m.setTitle(req.getTitle());
        m.setAchievedAt(req.getAchievedAt());
        m.setNotes(req.getNotes());
        return ResponseEntity.ok(milestoneRepository.save(m));
    }

    @GetMapping("/milestones/child/{childId}")
    public ResponseEntity<List<Milestone>> getMilestones(@PathVariable Long childId) {
        return ResponseEntity.ok(milestoneRepository.findByChildId(childId));
    }
}