package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByEmail(String email);

    Person findPersonById(Integer id);
}
