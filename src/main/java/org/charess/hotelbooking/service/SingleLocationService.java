package org.charess.hotelbooking.service;

import org.charess.hotelbooking.model.SingleLocation;
import org.hibernate.sql.Update;

import java.util.List;

public interface SingleLocationService {

    SingleLocation create(SingleLocation singleLocation);

    SingleLocation findById(Integer id);

    List<SingleLocation> findAll();

    SingleLocation Update(SingleLocation singleLocation);

    Boolean delete(Integer id);
}
