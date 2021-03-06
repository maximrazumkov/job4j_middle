package ru.job4j.hibernate.xml;

import javax.persistence.*;
import java.io.Serializable;

public class HistoryOwner implements Serializable {

    private Integer id;

    private Driver driver;

    private Car car;

    public HistoryOwner() {
    }

    public HistoryOwner(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof HistoryOwner) {
            return ((HistoryOwner) o).id.equals(this.id);
        }
        return false;
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        return result;
    }
}
