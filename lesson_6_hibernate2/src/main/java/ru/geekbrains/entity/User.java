package ru.geekbrains.entity;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},
            orphanRemoval = true)

    private List<Product> products;


    public User() {
    }

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", products=" + products +
                '}';
    }
}
