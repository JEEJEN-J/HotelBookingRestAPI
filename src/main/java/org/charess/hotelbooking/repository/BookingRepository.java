package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findBookingById(Integer id);
    Booking findBookingByPerson(Person person);

}
