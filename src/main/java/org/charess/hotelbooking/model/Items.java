package org.charess.hotelbooking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
public class Items extends Name {

    @ManyToOne
    @JoinTable(name = "booking_items", joinColumns = @JoinColumn(name = "items_id"), inverseJoinColumns = @JoinColumn(name = "booking_id"))
    private Booking booking;

    @OneToMany
    private List<Images> images;

    @ManyToOne
    @JoinTable(name = "category_items", joinColumns = @JoinColumn(name = "items_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "height", length = 15)
    private double height;

    @Column(name = "width", length = 15)
    private double width;

    @Column(name = "price", length = 15)
    private double price;


    public Items() {
    }

    @JsonIgnore
    public Booking getBooking() {
        return booking;
    }

    @JsonIgnore
    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
