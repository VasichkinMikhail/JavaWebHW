package ru.geekbrains.entity;

import java.util.List;
import java.util.Optional;

public interface Repository {
    List<User> findAll();

    Optional<User> findById(long id);

    void save(User user);

    void delete(long id);

    List<User> findProductByUserId(long id);

    List<User> findUserByProductId(long id);





}
