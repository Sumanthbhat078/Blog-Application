package com.blogApp.services;

import java.util.List;

import com.blogApp.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto catDto);
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	void deleteCategory(int categoryId);
	CategoryDto getCategory(int categoryId);
	List<CategoryDto> getCategories();
}
