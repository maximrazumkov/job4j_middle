package ru.job4j.hibernate.annotation;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "history_owner", schema = "public")
public class HistoryOwner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
