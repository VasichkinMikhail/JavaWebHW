package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Contact;
import ru.geekbrains.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main06 {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = factory.createEntityManager();
//           INSERT   1
//        em.getTransaction().begin();
//
//        User user = new User(null,"ivan","77887");
//        em.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact(null,"home phone","1567",user));
//        contacts.add(new Contact(null,"work phone","14544560098",user));
//        contacts.add(new Contact(null,"mobile phone","123213459",user));
//        contacts.add(new Contact(null,"mail","129934@5.com",user));
//        contacts.forEach(em::persist);
//
//
//        em.getTransaction().commit();
//              2
//        em.getTransaction().begin();
//
//        User user = em.find(User.class, 1L);
//        user.getContact().add(new Contact(null,"home addres","Moskow, Red square",user));
//        em.merge(user);
//
//        em.getTransaction().commit();
//          SELECT 1
//        List <User> users = em.createQuery("select distinct u " +
//                        "from User u " +
//                        "inner join u.contacts " +
//                        "where u.id = :id".User.class)
//                .setParameter("id", 1L)
//                .getResultList();
//
//        System.out.println("Users:" + users);
        // 2
//        List <Contact> contacts = em.createQuery("select distinct c " +
//                        "from Contact c " +
//                        "join fetch c.user " +
//                        "where c.type = :type",Contact.class)
//                .setParameter("type", "home phone")
//                .getResultList();
//        contacts.forEach(System.out::println);
        //DELETE 1
//        em.getTransaction().begin();
//
//        em.createQuery("delete from Contact c where c.id = :id")
//                        .setParameter("id",1L)
//                                .executeUpdate();
//
//        em.getTransaction().commit();
//      2
//        em.getTransaction().begin();
//
//        User user = em.find(User.class,1L);
//
//        user.getContact().remove(0);
//        em.merge(user);
//
//        em.getTransaction().commit();
//        3

        em.getTransaction().begin();
        User user = em.find(User.class,1L);
        em.remove(user);


        em.getTransaction().commit();

        em.close();

    }
}
