package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;
import ru.geekbrains.entity.RepositoryImpl;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        RepositoryImpl repository = new RepositoryImpl(factory);

        repository.findProductByUserId(1L);




    }
}
