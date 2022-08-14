package org.charess.hotelbooking.controller;


import org.charess.hotelbooking.model.Person;
import org.charess.hotelbooking.model.User;
import org.charess.hotelbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        List<?> objects = userService.getUsers();
        if (objects.toString().equals("[]"))
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        objects.forEach(o -> {
            System.out.println(o);
        });
        return ResponseEntity.ok(objects);
    }


    @RequestMapping(value = "id/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable("ID") Integer id) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = userService.findById(id);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by ID[" + id + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "username/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = userService.findByUsername(username);
        if (object != null)
            return ResponseEntity.ok(object);
        else
            return new ResponseEntity<>("Search by Username[" + username + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<?> sendEmail(@RequestParam(value = "email") String email,
                                       @RequestParam("subject") String subject,
                                       @RequestParam("message") String message) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = userService.sendMail(email, subject, message);
        return ResponseEntity.ok(object);
    }

    @RequestMapping(value = "email/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = userService.findByEmail(email);
        if (object != null)
            return ResponseEntity.ok(object);
        else
            return new ResponseEntity<>("Search by Username[" + email + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "email/{email}/password/{password}", method = RequestMethod.GET)
    public ResponseEntity<?> authenticate(@PathVariable("email") String email,
                                          @PathVariable("password") String password) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = null;

        if ((object = userService.findByEmail(email.trim())) == null)
            return new ResponseEntity<>("Error email : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        if ((object = userService.findByPassword(password.trim())) == null)
            return new ResponseEntity<>("Error password : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(object);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseEntity<?> getProfiles() {
        List<?> objects = userService.getProfiles();
        if (objects.size() == 0)
            return ResponseEntity.ok(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(objects);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody User user) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        try {
            if (user == null) {
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
            if (user.getId() == null) {
                if (userService.findByUsername(user.getPassword()) != null) {
                    return new ResponseEntity<>("Error password : " + HttpStatus.CONFLICT, textPlainHeaders, HttpStatus.CONFLICT);
                }
                if (userService.findByEmail(user.getPerson().getEmail()) != null) {
                    return new ResponseEntity<>("Error email : " + HttpStatus.CONFLICT, textPlainHeaders, HttpStatus.CONFLICT);
                }
            }
            return ResponseEntity.ok(userService.register(user));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }


    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public Person getPerson(@RequestParam("key") String key) {
        return userService.getPerson(key);
    }

}
