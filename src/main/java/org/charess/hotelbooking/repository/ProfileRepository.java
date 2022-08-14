package org.charess.hotelbooking.repository;

import org.charess.hotelbooking.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
