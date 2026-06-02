package com.autibridge.autibridge.child;

import com.autibridge.autibridge.child.dto.ChildRequest;
import com.autibridge.autibridge.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final UserRepository userRepository;

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Child addChild(String email, ChildRequest req) {
        User parent = getUser(email);
        Child child = new Child();
        child.setName(req.getName());
        child.setAge(req.getAge());
        child.setDiagnosisLevel(req.getDiagnosisLevel());
        child.setNotes(req.getNotes());
        child.setParent(parent);
        return childRepository.save(child);
    }

    public List<Child> getMyChildren(String email) {
        User parent = getUser(email);
        return childRepository.findByParentId(parent.getId());
    }

    public Child updateChild(String email, Long childId, ChildRequest req) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));
        if (!child.getParent().getEmail().equals(email)) {
            throw new RuntimeException("Not authorized");
        }
        child.setName(req.getName());
        child.setAge(req.getAge());
        child.setDiagnosisLevel(req.getDiagnosisLevel());
        child.setNotes(req.getNotes());
        return childRepository.save(child);
    }

    public void deleteChild(String email, Long childId) {
        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));
        if (!child.getParent().getEmail().equals(email)) {
            throw new RuntimeException("Not authorized");
        }
        childRepository.delete(child);
    }
}