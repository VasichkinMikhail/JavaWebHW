package ru.geekbrains.lesson_4_spring_boot.persist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
