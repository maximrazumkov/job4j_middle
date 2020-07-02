package ru.job4j.hibernate.annotation;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "engine", schema = "public")
public class Engine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
