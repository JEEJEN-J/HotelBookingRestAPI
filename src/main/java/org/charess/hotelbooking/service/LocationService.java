package org.charess.hotelbooking.service;


import org.charess.hotelbooking.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> search(String criteria);

}
