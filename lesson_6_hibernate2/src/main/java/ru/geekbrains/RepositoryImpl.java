package ru.geekbrains;


import ru.geekbrains.Repository;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;


public class RepositoryImpl implements Repository {

    private final EntityManagerFactory emFactory;

    public RepositoryImpl(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    @Override
    public List<User> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("from User u", User.class)
                        .getResultList()
        );

    }

    @Override
    public Optional<User> findById(long id) {
        return executeForEntityManager(
                em -> Optional.ofNullable(em.find(User.class, id))
        );
    }
    @Override
    public List<User> findProductByUserId(long id) {
        EntityManager em = emFactory.createEntityManager();
        List<User> products = em.createQuery("select distinct u " +
                        "from User u join fetch u.products " +
                        "where u.id = :id",User.class)
                .setParameter("id",id)
                .getResultList();
        System.out.println(products);
     return products;
    }

    @Override
    public List<User> findUserByProductId(long id) {
        EntityManager em = emFactory.createEntityManager();
      //  User user = em.find(User.class,id);
        List<User> products = em.createQuery("select distinct p from Product p inner join p.user where p.user = :id",User.class)
                .setParameter("id",id)
                .getResultList();
        System.out.println(products);
        return products;
    }

    @Override
    public void save(User user) {
        executeInTransaction(em -> {
            if (user.getId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
        });
    }

    @Override
    public void delete(long id) {
        executeInTransaction(
                em -> em.createQuery("delete from User where id = :id")
                        .setParameter("id", id)
                        .executeUpdate()
        );
    }



    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }




}




