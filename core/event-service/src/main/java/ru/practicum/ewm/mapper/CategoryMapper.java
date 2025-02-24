package ru.practicum.ewm.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.dto.NewCategoryDto;
import ru.practicum.ewm.model.Category;
import ru.practicum.ewm.dto.CategoryDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto map(Category category);

    Category mapToCategory(NewCategoryDto request);

    List<CategoryDto> mapToCategoryDto(List<Category> categories);
}
