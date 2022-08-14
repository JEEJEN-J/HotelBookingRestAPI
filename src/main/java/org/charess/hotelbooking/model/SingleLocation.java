package org.charess.hotelbooking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "singleLocation")
public class SingleLocation extends Name {

    @OneToOne
    private Location location;

    public SingleLocation() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
