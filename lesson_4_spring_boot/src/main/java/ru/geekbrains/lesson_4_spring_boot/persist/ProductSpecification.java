package ru.geekbrains.lesson_4_spring_boot.persist;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.lesson_4_spring_boot.service.dto.ProductDto;

import java.math.BigDecimal;

public final class ProductSpecification {

    public static Specification<Product> nameLike(String pattern){
        return (root,query,builder) -> builder.like(root.get("name"),"%" + pattern + "%");
    }
    public static Specification<ProductDto> minPriceFilter(BigDecimal minPrice){
        return (root,query,builder) -> builder.ge(root.get("price"),minPrice);
    }
    public static Specification<Product> maxPriceFilter(BigDecimal maxPrice){
        return (root,query,builder) -> builder.ge(root.get("price"),maxPrice);
    }
}
