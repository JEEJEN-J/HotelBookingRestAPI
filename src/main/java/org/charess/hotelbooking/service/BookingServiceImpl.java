package org.charess.hotelbooking.service;


import org.charess.hotelbooking.model.Audit;
import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Person;
import org.charess.hotelbooking.repository.BookingRepository;
import org.charess.hotelbooking.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private PersonRepository personRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, PersonRepository personRepository, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Booking create(Booking booking) {
        return booking == null ? null : this.bookingRepository.save(booking);
    }

    @Override
    public Booking findById(Integer id) {
        return this.bookingRepository.findBookingById(id);
    }

    @Override
    public Booking findByPerson(Person person) {
        Person person1 = this.personRepository.findPersonById(person.getId());
        return this.bookingRepository.findBookingByPerson(person1);
    }

    @Override
    public List<Booking> findAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Booking update(Booking booking) {
        return create(booking);
    }

    @Override
    public Boolean delete(Integer id) {
        Boolean bool = false;
        Booking booking = this.bookingRepository.findBookingById(id);
        if (booking != null) {
            this.bookingRepository.delete(booking);
            bool = true;
        }
        return bool;
    }
}
