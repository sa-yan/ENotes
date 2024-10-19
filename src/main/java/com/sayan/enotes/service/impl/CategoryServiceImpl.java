package com.sayan.enotes.service.impl;

import com.sayan.enotes.dto.CategoryDto;
import com.sayan.enotes.dto.CategoryResponseDto;
import com.sayan.enotes.model.Category;
import com.sayan.enotes.repository.CategoryRepository;
import com.sayan.enotes.service.CategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;
    private ModelMapper modelMapper;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {
//        Category category = new Category();

//        category.setName(categoryDto.getName());
//        category.setDescription(categoryDto.getDescription());
//        category.setIsActive(categoryDto.getIsActive());

        Category category = modelMapper.map(categoryDto, Category.class);

        category.setIsDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedAt(new Date());

        Category savedCategory =  repository.save(category);
        return !ObjectUtils.isEmpty(savedCategory);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = repository.findAll();

        return categoryList.stream()
                .map(cat -> modelMapper.map(cat, CategoryDto.class))
                .toList();
    }
}
