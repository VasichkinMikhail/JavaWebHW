package ru.geekbrains.lesson_4_spring_boot.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ProductDto;
import java.util.Optional;

public interface CartServise {
    Page<ProductDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort);

    void addToCart(Long id);

    void removeFromCart(long id);

}
