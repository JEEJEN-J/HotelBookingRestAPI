package org.charess.hotelbooking.service;

import org.charess.hotelbooking.model.Booking;
import org.charess.hotelbooking.model.Category;
import org.charess.hotelbooking.model.Images;
import org.charess.hotelbooking.model.Items;
import org.charess.hotelbooking.repository.ImagesRepository;
import org.charess.hotelbooking.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {

    private ItemsRepository itemsRepository;
    private ImagesRepository imagesRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository, ImagesRepository imagesRepository) {
        this.itemsRepository = itemsRepository;
        this.imagesRepository = imagesRepository;
    }

    @Override
    public Items create(Items items) {
        if (items.getImages().size() > 0) {
            List<Images> images = items.getImages();
            this.imagesRepository.saveAll(images);
            items.setImages(images);
        }
        return this.itemsRepository.save(items);
    }

    @Override
    public Items findById(Integer id) {
        return this.itemsRepository.findItemsById(id);
    }

    @Override
    public Items findByName(String name) {
        return this.itemsRepository.findByName(name);
    }

    @Override
    public List<Items> findByBooking(Booking booking) {
        return this.itemsRepository.findItemsByBooking(booking);
    }

    @Override
    public List<Items> findByCategory(Category category) {
        return this.itemsRepository.findItemsByCategory(category);
    }

    @Override
    public List<Items> findAll() {
        return this.itemsRepository.findAll();
    }


    @Override
    public Items update(Items items) {
        return create(items);
    }

    @Override
    public Boolean delete(Integer id) {
        Items items = findById(id);
        if (items == null)
            return false;
        this.itemsRepository.delete(items);
        return true;
    }
}
