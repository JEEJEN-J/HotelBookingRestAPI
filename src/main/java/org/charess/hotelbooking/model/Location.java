package org.charess.hotelbooking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "location")
public class Location extends Name implements Serializable {

    @Column(name = "nature", length = 30, nullable = false)
    private String nature;

    public Location() {
    }

    @ManyToOne
    @JoinColumn(name = "parent")
    private Location parent;

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    public String getFullname() {
        return getNameRecursively(this);
    }

    private String getNameRecursively(Location z) {
        return z.getParent() == null ? z.getName() : z.getName() + ", " + getNameRecursively(z.getParent());
    }

}
