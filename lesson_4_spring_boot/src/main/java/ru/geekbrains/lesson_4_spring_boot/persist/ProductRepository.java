package ru.geekbrains.lesson_4_spring_boot.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProductRepository  extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {


}
