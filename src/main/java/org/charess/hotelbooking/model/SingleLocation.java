package org.charess.hotelbooking.model;

import javax.persistence.*;

@Entity
@Table(name = "singleLocation")
public class SingleLocation extends Name {
    @OneToOne
    @JoinColumn(name = "singleLocation")
    @MapsId
    private Location singleLocation;

    public SingleLocation() {
    }

    public Location getSingleLocation() {
        return singleLocation;
    }

    public void setSingleLocation(Location singleLocation) {
        this.singleLocation = singleLocation;
    }
}
