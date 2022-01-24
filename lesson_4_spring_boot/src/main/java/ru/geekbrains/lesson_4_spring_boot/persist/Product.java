package ru.geekbrains.lesson_4_spring_boot.persist;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
@Entity
@Table(name = "products")
public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        @Column(nullable = false)
        private String name;

        @NotBlank
        @Column
        private String description;

        @PositiveOrZero
        @Column
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


