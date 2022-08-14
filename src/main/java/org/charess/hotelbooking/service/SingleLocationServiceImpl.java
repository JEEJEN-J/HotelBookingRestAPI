package org.charess.hotelbooking.service;

import org.charess.hotelbooking.model.SingleLocation;
import org.charess.hotelbooking.repository.SingleLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service("singleLocationService")
public class SingleLocationServiceImpl implements SingleLocationService {

    private SingleLocationRepository singleLocationRepository;

    @Autowired
    public SingleLocationServiceImpl(SingleLocationRepository singleLocationRepository) {
        this.singleLocationRepository = singleLocationRepository;
    }

    @Override
    public SingleLocation create(SingleLocation singleLocation) {
        return this.singleLocationRepository.save(singleLocation);
    }

    @Override
    public SingleLocation findById(Integer id) {
        return this.singleLocationRepository.findSingleLocationById(id);
    }

    @Override
    public List<SingleLocation> findAll() {
        return this.singleLocationRepository.findAll();
    }

    @Override
    public SingleLocation Update(SingleLocation singleLocation) {
        return create(singleLocation);
    }

    @Override
    public Boolean delete(Integer id) {
        Boolean bool = false;
        SingleLocation singleLocation = this.singleLocationRepository.findSingleLocationById(id);
        if (singleLocation != null) {
            this.singleLocationRepository.delete(singleLocation);
            bool = true;
        }
        return bool;
    }
}
