package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.SingleLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingleLocationRepository extends JpaRepository<SingleLocation, Integer> {
    SingleLocation findSingleLocationById(Integer id);
}
