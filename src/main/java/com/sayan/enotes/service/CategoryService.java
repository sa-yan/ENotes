package com.sayan.enotes.service;

import com.sayan.enotes.dto.CategoryDto;
import com.sayan.enotes.dto.CategoryResponseDto;
import com.sayan.enotes.model.Category;

import java.util.List;

public interface CategoryService{
    public Boolean saveCategory(CategoryDto categoryDto);
    public List<CategoryDto> getAllCategory();
}
