package com.cgesgin.blogging_platform_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgesgin.blogging_platform_api.model.entity.Tag;
import com.cgesgin.blogging_platform_api.service.TagService;

@RestController
@RequestMapping("/api")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tags", description = "API for tags operations")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public List<Tag> getAlltags() {
        return tagService.findAll();
    }

    @PostMapping("/tags")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        return ResponseEntity.ok(tagService.save(tag));
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        if (id == null || id <= 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PutMapping("/tags/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        if (id == null || id <= 0)
            return ResponseEntity.badRequest().build();
        var tagById = tagService.findById(id);
        if (tagById == null)
            return ResponseEntity.notFound().build();

        tag.setId(id);
        return ResponseEntity.ok(tagService.update(tag));
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        if (id <= 0 || tagService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
