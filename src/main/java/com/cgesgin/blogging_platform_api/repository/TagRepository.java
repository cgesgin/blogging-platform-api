package com.cgesgin.blogging_platform_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgesgin.blogging_platform_api.model.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    
}
