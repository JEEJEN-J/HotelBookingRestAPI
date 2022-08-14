package org.charess.hotelbooking.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Category;
import org.charess.hotelbooking.model.Items;
import org.charess.hotelbooking.service.BookingService;
import org.charess.hotelbooking.service.CategoryService;
import org.charess.hotelbooking.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/items")
public class ItemsController {

    private ItemsService itemsService;
    private CategoryService categoryService;
    private BookingService bookingService;

    @Autowired
    public ItemsController(ItemsService itemsService, CategoryService categoryService, BookingService bookingService) {
        this.itemsService = itemsService;
        this.categoryService = categoryService;
        this.bookingService = bookingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.itemsService.findAll());
    }

    @Operation(summary = "Créer un element, vous devez spécifier les propriétés ci-dessous dans la demande. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Items items) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        try {
            if (items == null) {
                return new ResponseEntity<>("Error category can't null : ", textPlainHeaders, HttpStatus.BAD_REQUEST);
            }
            if (items.getId() != null) {
                return new ResponseEntity<>("Error category exist : " + HttpStatus.CONFLICT, textPlainHeaders, HttpStatus.CONFLICT);
            }
            Object object = this.itemsService.create(items);
            System.out.println("Object : " + object);
            return ResponseEntity.ok(object);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemsById(@PathVariable("ID") Integer id) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = this.itemsService.findById(id);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by ID[" + id + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/category/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemsByCategory(@PathVariable("ID") Integer id) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Category category = this.categoryService.findById(id);
        if (category == null)
            return new ResponseEntity<>("Category doesn't exist [" + id + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        Object object = this.itemsService.findByCategory(category);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by categoryID[" + category + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/booking/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemsByBooking(@PathVariable("ID") Integer id) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Booking booking = this.bookingService.findById(id);
        if (booking == null)
            return new ResponseEntity<>("Booking doesn't exist [" + id + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        Object object = this.itemsService.findByBooking(booking);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by bookingID[" + booking + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getItemsByBooking(@PathVariable("name") String name) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        if (name.trim().isEmpty())
            return new ResponseEntity<>("Name can't null" + HttpStatus.EXPECTATION_FAILED, textPlainHeaders, HttpStatus.BAD_REQUEST);
        Object object = this.itemsService.findByName(name);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by name[" + name + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Modifier un element, vous devez spécifier les propriétés ci-dessous dans la demande. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Items items) {
        try {
            return ResponseEntity.ok(this.itemsService.update(items));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/delete/{ID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("ID") Integer id) {
        return ResponseEntity.ok(this.itemsService.delete(id));
    }

}
