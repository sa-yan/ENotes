package com.sayan.enotes.service.impl;

import com.sayan.enotes.dto.CategoryDto;
import com.sayan.enotes.model.Category;
import com.sayan.enotes.repository.CategoryRepository;
import com.sayan.enotes.service.CategoryService;
import com.sayan.enotes.util.Validation;
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
    private Validation validation;

    @Override
    public Boolean saveCategory(CategoryDto categoryDto) {

        //validation checking

        validation.categoryValidation(categoryDto);

        Category category = modelMapper.map(categoryDto, Category.class);

        if(ObjectUtils.isEmpty(category.getId())){
            category.setIsDeleted(false);
            category.setCreatedBy(1);
            category.setCreatedAt(new Date());
        }else{
            updateCategory(category);
        }


        Category savedCategory =  repository.save(category);
        return !ObjectUtils.isEmpty(savedCategory);
    }

    private void updateCategory(Category category) {
        Optional<Category> findById = repository.findById(category.getId());
        if(findById.isPresent()){
            Category existCategory = findById.get();
            category.setCreatedBy(existCategory.getCreatedBy());
            category.setCreatedAt(existCategory.getCreatedAt());
            category.setIsDeleted(existCategory.getIsDeleted());
            category.setUpdatedBy(1);
            category.setUpdatedAt(new Date());
        }
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
