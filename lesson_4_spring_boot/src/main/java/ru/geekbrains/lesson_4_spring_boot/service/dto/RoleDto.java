package ru.geekbrains.lesson_4_spring_boot.service.dto;

import javax.validation.constraints.NotBlank;


public class RoleDto {

    private Long id;

    @NotBlank
    private String name;


    public RoleDto() {
    }

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
