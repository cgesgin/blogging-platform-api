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

import com.cgesgin.blogging_platform_api.model.entity.Post;
import com.cgesgin.blogging_platform_api.service.CategoryService;
import com.cgesgin.blogging_platform_api.service.PostService;
import com.cgesgin.blogging_platform_api.service.TagService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Posts", description = "API for posts operations")
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final TagService tagService;

    public PostController(PostService postService, CategoryService categoryService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
        this.categoryService = categoryService;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        if (postService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.save(post));
    }

    @PostMapping("/posts/{postId}/categories/{categoryId}")
    public ResponseEntity<Post> addCategoryToPost(
            @PathVariable Long postId,
            @PathVariable Long categoryId) {
        if (postService.findById(postId) == null)
            return ResponseEntity.notFound().build();
        if (categoryService.findById(categoryId) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postService.addCategoryToPost(postId, categoryId));
    }

    @DeleteMapping("/posts/{postId}/categories/{categoryId}")
    public ResponseEntity<Post> removeCategoryFromPost(
            @PathVariable Long postId,
            @PathVariable Long categoryId) {

        if (postService.findById(postId) == null)
            return ResponseEntity.notFound().build();
        if (categoryService.findById(categoryId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(postService.removeCategoryFromPost(postId, categoryId));
    }

    @PostMapping("/posts/{postId}/tags/{tagId}")
    public ResponseEntity<Post> addTagToPost(
            @PathVariable Long postId,
            @PathVariable Long tagId) {
        if (postService.findById(postId) == null)
            return ResponseEntity.notFound().build();
        if (tagService.findById(tagId) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postService.addTagToPost(postId, tagId));
    }

    @DeleteMapping("/posts/{postId}/tags/{tagId}")
    public ResponseEntity<Post> removeTagFromPost(
            @PathVariable Long postId,
            @PathVariable Long tagId) {

        if (postService.findById(postId) == null)
            return ResponseEntity.notFound().build();
        if (tagService.findById(tagId) == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(postService.removeTagFromPost(postId, tagId));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        var post = postService.findById(id);
        if (post == null)
            return ResponseEntity.notFound().build();
        postDetails.setId(id);
        return ResponseEntity.ok(postService.update(postDetails));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        var post = postService.findById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
