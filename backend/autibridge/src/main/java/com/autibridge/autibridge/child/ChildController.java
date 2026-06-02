package com.autibridge.autibridge.child;

import com.autibridge.autibridge.child.dto.ChildRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/children")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping
    public ResponseEntity<Child> addChild(@AuthenticationPrincipal UserDetails u,
                                          @Valid @RequestBody ChildRequest req) {
        return ResponseEntity.ok(childService.addChild(u.getUsername(), req));
    }

    @GetMapping
    public ResponseEntity<List<Child>> getMyChildren(@AuthenticationPrincipal UserDetails u) {
        return ResponseEntity.ok(childService.getMyChildren(u.getUsername()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Child> updateChild(@AuthenticationPrincipal UserDetails u,
                                             @PathVariable Long id,
                                             @Valid @RequestBody ChildRequest req) {
        return ResponseEntity.ok(childService.updateChild(u.getUsername(), id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChild(@AuthenticationPrincipal UserDetails u,
                                            @PathVariable Long id) {
        childService.deleteChild(u.getUsername(), id);
        return ResponseEntity.noContent().build();
    }
}