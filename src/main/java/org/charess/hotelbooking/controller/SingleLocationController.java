package org.charess.hotelbooking.controller;


import org.charess.hotelbooking.repository.SingleLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/singleLocation")
public class SingleLocationController {

    private SingleLocationRepository singleLocationRepository;

    @Autowired
    public SingleLocationController(SingleLocationRepository singleLocationRepository){
        this.singleLocationRepository=singleLocationRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(this.singleLocationRepository.findAll());
    }

    @RequestMapping(value = "/{ID}", method = RequestMethod.GET)
    public ResponseEntity<?> getSingLocationById(@PathVariable("ID") Integer id) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = this.singleLocationRepository.findById(id);
        try {
            object.toString();
            return ResponseEntity.ok(object);
        } catch (javax.persistence.EntityNotFoundException ex) {
            return new ResponseEntity<>("Search by ID[" + id + "] : " + HttpStatus.NOT_FOUND, textPlainHeaders, HttpStatus.NOT_FOUND);
        }
    }
}
