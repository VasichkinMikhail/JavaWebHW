package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.ProductRepositoryImpl;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManagerFactory;

public class Main {
    private static EntityManagerFactory emFactory = null;


    public static void main(String[] args) {
        ProductRepositoryImpl repository = new ProductRepositoryImpl();
        emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
//     SAVE
//     User user = new User(null,"Bobi","passs5");
//     repository.save(user);
//     UPDATE
//        User user = new User(2L,"Jack","666");
//        repository.update(user);
//     DELETE
//        repository.deleteById(3L);
//      SELECT
//        repository.findById(2L);

//        repository.findAll();
    }
    public static EntityManagerFactory em() {
        return emFactory;
    }

    }

