package com.blogApp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.entities.Category;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payloads.CategoryDto;
import com.blogApp.repositories.CategoryRepo;
import com.blogApp.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
    private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto catDto) {
		Category cat=modelMapper.map(catDto, Category.class);
		return modelMapper.map(categoryRepo.save(cat),CategoryDto.class);
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		return modelMapper.map(categoryRepo.save(cat), CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		return modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return catDtos;
	}

}
