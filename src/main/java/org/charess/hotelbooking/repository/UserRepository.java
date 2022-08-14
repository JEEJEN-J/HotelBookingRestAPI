package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByPassword(String password);
    @Query("select u from User u where u.person.id <> 1 order by u.person.id desc ")
    List<User> find();
}
