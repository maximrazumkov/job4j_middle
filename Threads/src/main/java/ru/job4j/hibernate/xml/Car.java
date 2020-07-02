package ru.job4j.hibernate.xml;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Car implements Serializable {

    private Integer id;

    private Set<Driver> drivers = new HashSet<>();

    private Engine engine_id;

    public Car() {
    }

    public Car(Engine engine_id) {
        this.engine_id = engine_id;
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine_id;
    }

    public void setEngine(Engine engine_id) {
        this.engine_id = engine_id;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Car) {
            return ((Car) o).id.equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
}
