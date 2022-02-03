package ru.geekbrains.entity;


import ru.geekbrains.Main05;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpl implements UserRepository{

    private final EntityManagerFactory factory;

    public UserRepositoryImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }


    public void save(User user) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new User(user.getId(), user.getUsername(), user.getPassword()));
        em.getTransaction().commit();
        em.close();
    }



    public void update(User user) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }
    @Override
    public void deleteById(Long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
    @Override
    public Optional<User> findById(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
            return Optional.ofNullable(em.find(User.class, id));
        }finally {
           em.close();
        }


    }
    @Override
    public List<User> findAll() {
        EntityManager em = factory.createEntityManager();
        try {
            return em.createQuery("from User u ", User.class)
                    .getResultList();
        } finally {
            em.close();
        }

    }



}




