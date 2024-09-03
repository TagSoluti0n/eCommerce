package com.tagsolution.ecommercebackend.service;

import com.tagsolution.ecommercebackend.model.request.CategoryRequest;
import com.tagsolution.ecommercebackend.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse createCategory(CategoryRequest request);

    CategoryResponse updateCategory(Long id, CategoryRequest request);

    void deleteCategory(Long id);
}
