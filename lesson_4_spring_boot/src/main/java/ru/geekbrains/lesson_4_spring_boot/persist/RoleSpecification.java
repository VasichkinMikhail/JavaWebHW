package ru.geekbrains.lesson_4_spring_boot.persist;

import org.springframework.data.jpa.domain.Specification;

public class RoleSpecification {
    public static Specification<Role> nameLike(String pattern){
        return (root,query,builder) -> builder.like(root.get("name"),"%" + pattern + "%");
    }
}
