package ru.geekbrains.lesson_4_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson_4_spring_boot.persist.*;
import ru.geekbrains.lesson_4_spring_boot.service.dto.CategoryDto;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Page<CategoryDto> findAll(Optional<String> categoryFilter, Integer page, Integer size, String sort) {
        Specification<Category> spec = Specification.where(null);
        if (categoryFilter.isPresent() && !categoryFilter.get().isBlank()) {
            spec = spec.and(CategorySpecification.nameLike(categoryFilter.get()));
        }
        return categoryRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sort)))
                .map(CategoryServiceImpl::convertToDto);
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        return categoryRepository.findById(id).map(CategoryServiceImpl::convertToDto);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = new Category(
                categoryDto.getId(),
                categoryDto.getName());

        Category saved = categoryRepository.save(category);
        return convertToDto(saved);

    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);

    }
    private static CategoryDto convertToDto(Category category) {
        return new CategoryDto
                (category.getId(), category.getName());
    }
}
