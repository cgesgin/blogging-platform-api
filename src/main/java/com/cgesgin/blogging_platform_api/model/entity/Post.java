package com.cgesgin.blogging_platform_api.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
   
    @Column(name = "content")
    private String content;
   
    @ManyToMany
    @JoinTable(
        name = "post_category",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
   
    @ManyToMany
    @JoinTable(
        name = "post_tag",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getPosts().add(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getPosts().remove(this);
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
        tag.getPosts().remove(this);
    }

}
