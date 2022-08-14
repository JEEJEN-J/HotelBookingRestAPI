package org.charess.hotelbooking.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category extends Name implements Serializable {
}
