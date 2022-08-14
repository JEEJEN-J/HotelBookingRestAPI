package org.charess.hotelbooking.service;


import org.charess.hotelbooking.model.Audit;
import org.charess.hotelbooking.model.Person;
import org.charess.hotelbooking.model.Profile;
import org.charess.hotelbooking.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    boolean sendMail(String toEmail, String subject, String message);

    Audit inject(Audit audit);

    User findById(Integer id);

    List<User> getUsers();

    List<Profile> getProfiles();

    Person findByEmail(String email);

    User findByPassword(String password);

    Object register(User user);

    Object update(User user);

    Person getPerson(String key);
}
