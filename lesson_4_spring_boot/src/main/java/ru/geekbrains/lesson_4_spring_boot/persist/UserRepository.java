package ru.geekbrains.lesson_4_spring_boot.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("select distinct u from User u left join fetch u.roles where u.login=:login")
    Optional<User> findByLogin(@Param("login") String login);
}
