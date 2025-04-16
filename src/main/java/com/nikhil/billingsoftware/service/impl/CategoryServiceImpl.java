package com.nikhil.billingsoftware.service.impl;

import com.nikhil.billingsoftware.entity.CategoryEntity;
import com.nikhil.billingsoftware.io.CategoryRequest;
import com.nikhil.billingsoftware.io.CategoryResponse;
import com.nikhil.billingsoftware.repo.CategoryRepository;
import com.nikhil.billingsoftware.service.CategoryService;
import com.nikhil.billingsoftware.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUploadService fileUploadService;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest, MultipartFile file) {
        String imgUrl= fileUploadService.uploadFile(file);
        CategoryEntity newCategory = convertToEntity(categoryRequest);
        newCategory.setImageUrl(imgUrl);
        newCategory=categoryRepository.save(newCategory);
        return convertToResponse(newCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryEntity -> convertToResponse(categoryEntity)).
                collect(Collectors.toList());
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByCategoryId(categoryId).orElseThrow(()->new  RuntimeException("category not found"+categoryId));
        fileUploadService.deleteFile(categoryEntity.getImageUrl());
        categoryRepository.delete(categoryEntity);
    }

    private CategoryResponse convertToResponse(CategoryEntity newCategory) {
        return CategoryResponse.builder()
                .categoryId(newCategory.getCategoryId())
                .name(newCategory.getName())
                .description(newCategory.getDescription())
                .imageUrl(newCategory.getImageUrl())
                .createdAt(newCategory.getCreatedAt())
                .bgColor(newCategory.getBgColor())
                .updatedAt(newCategory.getUpdatedAt())
                .build();


    }

    private CategoryEntity convertToEntity(CategoryRequest categoryRequest) {
        return CategoryEntity.builder()
                .categoryId(UUID.randomUUID().toString())
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .bgColor(categoryRequest.getBgColor())
                .build();
    }
}
