package ru.geekbrains.entity;


import ru.geekbrains.Main;

import javax.persistence.EntityManager;
import java.util.List;


public class ProductRepositoryImpl{


    public void save(User user) {
        EntityManager em = Main.em().createEntityManager();
        em.getTransaction().begin();
        em.persist(new User(user.getId(),user.getUsername(),user.getPassword()));
        em.getTransaction().commit();
        em.close();
    }
    public void update(User user){
        EntityManager em = Main.em().createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteById(Long id) {
        EntityManager em = Main.em().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
    public User findById(Long id) {
        EntityManager em = Main.em().createEntityManager();
        User user = em.find(User.class,id);
        List<User> users = em.createQuery("select u from User u where u.id = :id  ", User.class)
                .setParameter("id",id)
                        .getResultList();
        System.out.println(users);
        return user;

    }
    public List<User> findAll() {
        EntityManager em = Main.em().createEntityManager();
        List<User> users = em.createQuery("select u from User u ", User.class)
                .getResultList();
        System.out.println(users);
        return users;

    }


}

