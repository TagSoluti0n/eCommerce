package com.tagsolution.ecommercebackend.service.impl;

import com.tagsolution.ecommercebackend.exception.GenericExceptionResponse;
import com.tagsolution.ecommercebackend.model.request.CategoryRequest;
import com.tagsolution.ecommercebackend.model.response.CategoryResponse;
import com.tagsolution.ecommercebackend.repository.CategoryRepository;
import com.tagsolution.ecommercebackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private static final String UPDATE_EXCEPTION_MESSAGE = "Ya existe una categoria con el mismo titulo.";

    @Override
    public List<CategoryResponse> getAllCategories() {
        var categories = categoryRepository.findAll();
        return CategoryResponse.toDtoList(categories);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        var foundCategory = categoryRepository.findByTitle(request.getTitle());

        if (foundCategory.isPresent()) {
            var category = foundCategory.get();
            category.setTitle(request.getTitle());
            category.setDescription(request.getDescription());
            category.setImageUrl(request.getImage());
            List<CategoryResponse> savedCategory = CategoryResponse.toDtoList(List.of(categoryRepository.save(category)));
            return savedCategory.get(0);
        } else {
            List<CategoryResponse> savedCategory = CategoryResponse.toDtoList(List.of(categoryRepository.save(CategoryRequest.toEntity(request))));
            return savedCategory.get(0);
        }
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        var foundCategory = categoryRepository.findById(id).orElseThrow();
        var foundByTitle = categoryRepository.findByTitle(request.getTitle());

        if (!foundByTitle.isPresent()) {
            foundCategory.setTitle(request.getTitle());
            foundCategory.setDescription(request.getDescription());
            foundCategory.setImageUrl(request.getImage());
            List<CategoryResponse> savedCategory = CategoryResponse.toDtoList(List.of(categoryRepository.save(foundCategory)));
            return savedCategory.get(0);
        } else {
            throw new GenericExceptionResponse(UPDATE_EXCEPTION_MESSAGE, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @Override
    public void deleteCategory(Long id) {
        var foundCategory = categoryRepository.findById(id).orElseThrow();
        if (!foundCategory.isDeleted()) {
            categoryRepository.deleteById(id);
        }
    }
}
