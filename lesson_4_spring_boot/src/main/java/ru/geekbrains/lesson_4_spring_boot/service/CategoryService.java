package ru.geekbrains.lesson_4_spring_boot.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.lesson_4_spring_boot.service.dto.CategoryDto;
import java.util.Optional;

public interface CategoryService {

    Page<CategoryDto> findAll(Optional<String> categoryFilter, Integer page, Integer size, String sort);

    Optional<CategoryDto> findById(Long id);

    CategoryDto save(CategoryDto category);

    void deleteById(Long id);
}
