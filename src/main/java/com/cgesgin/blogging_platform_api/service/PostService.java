package com.cgesgin.blogging_platform_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cgesgin.blogging_platform_api.model.entity.Category;
import com.cgesgin.blogging_platform_api.model.entity.Post;
import com.cgesgin.blogging_platform_api.model.entity.Tag;
import com.cgesgin.blogging_platform_api.model.service.IGenericService;
import com.cgesgin.blogging_platform_api.repository.PostRepository;
import com.cgesgin.blogging_platform_api.repository.TagRepository;
import com.cgesgin.blogging_platform_api.repository.CategoryRepository;

@Service
public class PostService implements IGenericService<Post> {

    private PostRepository postRepository;
    private CategoryRepository categoryRepository;
    private TagRepository tagRepository;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository,
            TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long postId) {
        var post = postRepository.findById(postId)
                .orElse(null);
        if (post == null)
            return null;

        return post;

    }

    public Post save(Post post) {

        if (post.getId() == null || post.getId() > 0)
            post.setId(null);
        if (post.getCategories() == null)
            post.setCategories(new ArrayList<>());
        if (post.getTags() == null)
            post.setTags(new ArrayList<>());
        return postRepository.save(post);
    }

    public Post addCategoryToPost(Long postId, Long categoryId) {
        Post post = postRepository.findById(postId)
                .orElse(null);
        if (post == null)
            return null;

        Category category = categoryRepository.findById(categoryId)
                .orElse(null);
        if (category == null)
            return null;

        post.addCategory(category);
        return postRepository.save(post);
    }

    public Post removeCategoryFromPost(Long postId, Long categoryId) {
        Post post = postRepository.findById(postId)
                .orElse(null);
        if (post == null)
            return null;

        Category category = categoryRepository.findById(categoryId)
                .orElse(null);
        if (category == null)
            return null;

        post.removeCategory(category);
        return postRepository.save(post);
    }

    public Post addTagToPost(Long postId, Long tagId) {
        Post post = postRepository.findById(postId)
                .orElse(null);
        if (post == null)
            return null;

        Tag tag = tagRepository.findById(tagId)
                .orElse(null);

        if (tag == null)
            return null;

        post.addTag(tag);
        return postRepository.save(post);
    }

    public Post removeTagFromPost(Long postId, Long tagId) {
        Post post = postRepository.findById(postId)
                .orElse(null);
        if (post == null)
            return null;

        Tag tag = tagRepository.findById(tagId)
                .orElse(null);

        if (tag == null)
            return null;

        post.removeTag(tag);
        return postRepository.save(post);
    }

    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post update(Post entity) {
        Post existingPost = postRepository.findById(entity.getId())
                .orElse(null);
        if (existingPost == null)
            return null;

        existingPost.setTitle(entity.getTitle());
        existingPost.setContent(entity.getContent());

        return postRepository.save(existingPost);
    }

}
