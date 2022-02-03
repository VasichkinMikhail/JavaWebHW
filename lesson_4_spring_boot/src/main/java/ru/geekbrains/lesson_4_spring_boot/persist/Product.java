package ru.geekbrains.lesson_4_spring_boot.persist;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Product {

        private Long id;
        @NotBlank
        private String name;

        private String description;

        private BigDecimal price;

        public Product() {
        }

        public Product(Long id, String name, BigDecimal price, String description) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}


