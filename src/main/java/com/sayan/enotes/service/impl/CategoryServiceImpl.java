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
import java.util.Optional;

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
        List<Category> categoryList = repository.findByIsDeletedFalse();

        return categoryList.stream()
                .map(cat -> modelMapper.map(cat, CategoryDto.class))
                .toList();
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Optional<Category> findByCategory = repository.findByIdAndIsDeletedFalse(id);
        if(findByCategory.isPresent()){
            Category category = findByCategory.get();
            return modelMapper.map(category, CategoryDto.class);
        }
        return null;
    }

    @Override
    public Boolean deleteCategoryById(Integer id) {
        Optional<Category> findByCategory = repository.findById(id);
        if(findByCategory.isPresent()){
            Category category = findByCategory.get();
            category.setIsDeleted(true);
            repository.save(category);
            return true;
        }
        return false;
    }
}
