package com.nikhil.billingsoftware.service;

import com.nikhil.billingsoftware.entity.CategoryEntity;
import com.nikhil.billingsoftware.io.CategoryRequest;
import com.nikhil.billingsoftware.io.CategoryResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    CategoryResponse addCategory(CategoryRequest categoryRequest, MultipartFile file);

    List<CategoryResponse> getAllCategories();

    void deleteCategoryById( String categoryId);
}
