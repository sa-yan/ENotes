package com.sayan.enotes.service;

import com.sayan.enotes.model.Category;

import java.util.List;

public interface CategoryService{
    public Boolean saveCategory(Category category);
    public List<Category> getAllCategory();
}
