package ru.geekbrains.lesson_4_spring_boot.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.lesson_4_spring_boot.service.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Page<UserDto> findAll(Optional<String> nameFilter, Integer page, Integer size, String sort);

    Optional<UserDto> findById(Long id);

    UserDto save(UserDto userDto);

    void deleteById(Long id);
}
