package ru.job4j.hibernate.xml;

import javax.persistence.*;
import java.io.Serializable;

public class Engine implements Serializable {

    private Integer id;

    public Engine() {
    }

    public Engine(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Engine) {
            Engine engine = (Engine) o;
            return this.id.equals(engine.id);
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
