package ru.geekbrains.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;
    @OneToMany(mappedBy = "customer",
            orphanRemoval = true)
    private List<Lineitem> lineitems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Lineitem> getLineitems() {
        return lineitems;
    }

    public void setLineitems(List<Lineitem> lineitems) {
        this.lineitems = lineitems;
    }
}
