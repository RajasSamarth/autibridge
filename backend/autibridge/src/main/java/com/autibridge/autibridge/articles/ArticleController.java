package com.autibridge.autibridge.articles;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return ResponseEntity.ok(articleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                articleRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Article not found"))
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Article>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(articleRepository.findByCategory(category));
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        return ResponseEntity.ok(articleRepository.save(article));
    }
}