package com.cgesgin.blogging_platform_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cgesgin.blogging_platform_api.model.entity.Tag;
import com.cgesgin.blogging_platform_api.model.service.IGenericService;
import com.cgesgin.blogging_platform_api.repository.TagRepository;

@Service
public class TagService implements IGenericService<Tag> {

    private TagRepository tagRepository;

    public TagService( TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag save(Tag entity) {
        if (entity.getId() == null || entity.getId() > 0)
            entity.setId(null);
        if (entity.getPosts() == null)
            entity.setPosts(new ArrayList<>());
        return tagRepository.save(entity);
    }

    @Override
    public Tag update(Tag entity) {
        var tag = tagRepository.findById(entity.getId())
                .orElse(null);

        if (tag == null)
            return null;

        tag.setName(entity.getName());
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
       tagRepository.deleteById(id);
    }

    @Override
    public Tag findById(Long id) {
        var entity = tagRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return entity;
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

}
