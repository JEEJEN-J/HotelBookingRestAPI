package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items, Integer> {
}
