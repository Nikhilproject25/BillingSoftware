package com.nikhil.billingsoftware.controller;

import com.nikhil.billingsoftware.entity.CategoryEntity;
import com.nikhil.billingsoftware.io.CategoryRequest;
import com.nikhil.billingsoftware.io.CategoryResponse;
import com.nikhil.billingsoftware.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse addCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.addCategory(categoryRequest);
    }

    @RequestMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.LOCKED)
    public void deleteCategoryByName(@PathVariable String name) {
        categoryService.deleteCategoryByName(name);
    }

}
