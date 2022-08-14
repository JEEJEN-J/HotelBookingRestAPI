package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Category;
import org.charess.hotelbooking.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
    @Query("select item from Items item where lower(item.name) like Concat('%', Concat(?1,'%'))")
    List<Items> search(String criteria);

    Items findItemsById(Integer id);

    Items findByName(String name);

    List<Items> findItemsByCategory(Category category);

    List<Items> findItemsByBooking(Booking booking);
}
