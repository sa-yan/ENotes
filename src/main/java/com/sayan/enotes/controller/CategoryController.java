package com.sayan.enotes.controller;


import com.sayan.enotes.model.Category;
import com.sayan.enotes.service.CategoryService;
import jakarta.servlet.ServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/save-category")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
       Boolean isSaved = categoryService.saveCategory(category);

       if (isSaved) {
           return new ResponseEntity<>("SUCCESSFULLY SAVED!!", HttpStatus.CREATED);
       }else{
           return new ResponseEntity<>("FAILED TO SAVE", HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping("/get-categories")
    public ResponseEntity<List<Category>> getAllCategories(ServletRequest servletRequest) {
        List<Category> categoryList = categoryService.getAllCategory();

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

}
