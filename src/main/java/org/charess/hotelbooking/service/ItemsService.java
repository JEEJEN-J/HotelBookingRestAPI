package org.charess.hotelbooking.service;

import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Category;
import org.charess.hotelbooking.model.Items;

import java.util.List;

public interface ItemsService {

    Items create(Items items);

    Items findById(Integer id);

    Items findByName(String name);

    List<Items> findByBooking(Booking booking);

    List<Items> findByCategory(Category category);

    List<Items> findAll();

    Items update(Items items);

    Boolean delete(Integer id);
}
