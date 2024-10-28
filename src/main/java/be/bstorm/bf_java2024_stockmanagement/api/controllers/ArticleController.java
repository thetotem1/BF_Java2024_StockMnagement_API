package be.bstorm.bf_java2024_stockmanagement.api.controllers;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.bll.services.CategoryService;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import be.bstorm.bf_java2024_stockmanagement.api.models.dtos.article.ArticleDTO;
import be.bstorm.bf_java2024_stockmanagement.api.models.dtos.article.ArticleDetailsDTO;
import be.bstorm.bf_java2024_stockmanagement.api.models.forms.article.ArticleForm;
import be.bstorm.bf_java2024_stockmanagement.api.models.forms.article.ArticleUpdateForm;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getArticles() {

        List<ArticleDTO> articles = articleService.findAll().stream()
                .map(ArticleDTO::fromArticle)
                .toList();
//        return ResponseEntity.status(200).body(articles);
        return ResponseEntity.ok(articles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailsDTO> getArticle(@PathVariable UUID id) {
        ArticleDetailsDTO dto = ArticleDetailsDTO.fromArticle(articleService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> createArticle(
            @Valid @RequestBody ArticleForm articleForm,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {

            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException("Validation Error: " + errors);
        }

        Category category = categoryService.findById(articleForm.categoryId());
        Article article = articleForm.toArticle();
        article.setCategory(category);
        Article createdArticle = articleService.save(article, articleForm.image());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdArticle.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateArticle(
            @Valid @RequestBody ArticleUpdateForm articleForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {

            List<String> errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException("Validation Error: " + errors);
        }

        Article article = articleForm.toArticle();
        article.setCategory(categoryService.findById(articleForm.categoryId()));
        articleService.update(article, articleForm.image());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable UUID id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

