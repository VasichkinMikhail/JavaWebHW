package ru.geekbrains.lesson_4_spring_boot.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository  extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    @Override
    void deleteById(Long id);
}
