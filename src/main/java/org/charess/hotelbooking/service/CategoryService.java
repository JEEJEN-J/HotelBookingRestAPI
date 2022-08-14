package org.charess.hotelbooking.service;


import org.charess.hotelbooking.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> list();

    List<Category> search(String criteria);
    Category findById(Integer id);

    Category save(Category category);

    Category update(Category category);
}
