package ru.job4j.hibernate.xml;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Driver implements Serializable {

    private Integer id;

    private Set<Car> cars = new HashSet<>();

    public Driver() {
    }

    public Driver(Set<Car> cars) {
        this.cars = cars;
    }

    public Driver(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Driver) {
            return ((Driver) o).id.equals(this.id);
        }
        return false;
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
}
