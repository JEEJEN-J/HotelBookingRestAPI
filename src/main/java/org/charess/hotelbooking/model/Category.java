package org.charess.hotelbooking.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends Name implements Serializable {

    @OneToMany
    private List<Items> items;

    public Category() {
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
