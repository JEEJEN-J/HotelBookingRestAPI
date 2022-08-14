package org.charess.hotelbooking.service;

import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Person;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking);

    Booking findById(Integer id);

    //    Booking findByCode(String code);
    Booking findByPerson(Person person);

    List<Booking> findAll();

    Booking update(Booking booking);

    Boolean delete(Integer id);
}
