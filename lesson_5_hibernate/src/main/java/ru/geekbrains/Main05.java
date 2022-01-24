package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;

public class Main05 {


    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
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


    }

