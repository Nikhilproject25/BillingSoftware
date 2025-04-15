package com.nikhil.billingsoftware.service;

import com.nikhil.billingsoftware.io.CategoryRequest;
import com.nikhil.billingsoftware.io.CategoryResponse;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest categoryRequest);
}
