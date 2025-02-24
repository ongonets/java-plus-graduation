package ru.practicum.ewm.service;

import ru.practicum.ewm.dto.CategoryDto;
import ru.practicum.ewm.dto.NewCategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(NewCategoryDto request);

    void deleteCategory(long id);

    CategoryDto updateCategory(NewCategoryDto request, long id);

    CategoryDto findCategoryById(long id);

    List<CategoryDto> findCategories(int from, int size);
}
