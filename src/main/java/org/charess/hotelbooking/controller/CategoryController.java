package org.charess.hotelbooking.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.charess.hotelbooking.model.Category;
import org.charess.hotelbooking.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(categoryService.list());
    }


    @RequestMapping(value = "/search/{criteria}", method = RequestMethod.GET)
    public ResponseEntity<?> search(@PathVariable(value = "criteria") String criteria) {
        return ResponseEntity.ok(categoryService.search(criteria));
    }


    @Operation(summary = "Créer une category, vous devez spécifier les propriétés ci-dessous dans la demande. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Category category) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        try {
            if (category == null) {
                return new ResponseEntity<>("Error category can't null : ", textPlainHeaders, HttpStatus.BAD_REQUEST);
            }
            if (category.getId() != null) {
                return new ResponseEntity<>("Error category exist : " + HttpStatus.CONFLICT, textPlainHeaders, HttpStatus.CONFLICT);
            }
            Object object = this.categoryService.save(category);
            System.out.println("Object : " + object);
            return ResponseEntity.ok(object);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }


    @Operation(summary = "Modifier un category, vous devez spécifier les propriétés ci-dessous dans la demande. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Category category) {
        try {
            return ResponseEntity.ok(categoryService.update(category));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
    }

}
