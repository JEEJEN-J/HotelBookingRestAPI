package org.charess.hotelbooking.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "booking")
public class Booking extends Name {

    @OneToOne
    @JoinColumn(name = "person")
    @MapsId
    private Person person;

    @OneToMany
    private List<Items> items;

    @Column(name = "numberOfDay", length = 3)
    private Integer numberOfDay;

    @Column(name = "startDate", length = 100)
    private LocalDateTime startDate;

    @Column(name = "endDate", length = 100)
    private LocalDateTime endDate;

    @Column(name = "totalPrice", length = 15)
    private double totalPrice;


    public Booking() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Integer getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(Integer numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
