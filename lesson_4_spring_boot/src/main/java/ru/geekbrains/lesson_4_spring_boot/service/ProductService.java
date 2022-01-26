package ru.geekbrains.lesson_4_spring_boot.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ProductDto;
import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort);

    Optional<ProductDto> findById(Long id);

    ProductDto save(ProductDto product);

    void deleteById(Long id);
}
