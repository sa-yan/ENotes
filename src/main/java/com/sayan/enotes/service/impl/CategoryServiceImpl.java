package com.sayan.enotes.service.impl;

import com.sayan.enotes.model.Category;
import com.sayan.enotes.repository.CategoryRepository;
import com.sayan.enotes.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;

    @Override
    public Boolean saveCategory(Category category) {
        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedAt(new Date());
       Category savedCategory =  repository.save(category);
        return !ObjectUtils.isEmpty(savedCategory);
    }

    @Override
    public List<Category> getAllCategory() {
        return repository.findAll();
    }
}
