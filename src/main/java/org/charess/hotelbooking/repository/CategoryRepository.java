package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where lower(c.name) like Concat('%', Concat(?1,'%'))")
    List<Category> search(String criteria);
    Category findCategoryById(Integer integer);
}
