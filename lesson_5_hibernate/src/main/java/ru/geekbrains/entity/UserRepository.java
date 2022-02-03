package ru.geekbrains.entity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void deleteById(Long id);

    Optional<User> findById(Long id);

    List<User> findAll();

    void save(User user);




}
