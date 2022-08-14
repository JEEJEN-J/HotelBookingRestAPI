package org.charess.hotelbooking.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Person;
import org.charess.hotelbooking.repository.PersonRepository;
import org.charess.hotelbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/booking")
public class BookingController {

    private BookingService bookingService;
    private PersonRepository personRepository;

    @Autowired
    public BookingController(BookingService bookingService, PersonRepository personRepository) {
        this.bookingService = bookingService;
        this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookingById(@PathVariable("ID") Integer id) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = bookingService.findById(id);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by ID[" + id + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/person/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> getBookingByPerson(@RequestBody Integer id) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Person person = personRepository.findPersonById(id);
        if (person == null)
            return new ResponseEntity<>("Person doesn't exist [" + id + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        Object object = bookingService.findByPerson(person);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by personID[" + person + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Créer une reservation, vous devez spécifier les propriétés ci-dessous dans la demande. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Booking booking) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        try {
            if (booking == null) {
                return new ResponseEntity<>("Error booking can't null : ", textPlainHeaders, HttpStatus.BAD_REQUEST);
            }
            if (booking.getId() != null) {
                return new ResponseEntity<>("Error booking already exist : " + HttpStatus.CONFLICT, textPlainHeaders, HttpStatus.CONFLICT);
            }
            Object object = this.bookingService.create(booking);
            System.out.println("Booking Object : " + object);
            return ResponseEntity.ok(object);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Operation(summary = "Modifier une reservation, vous devez spécifier les propriétés ci-dessous dans la demande. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Booking booking) {
        try {
            return ResponseEntity.ok(bookingService.update(booking));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
