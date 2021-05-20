package app.postgresql.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "make")
public class Make implements Serializable {
    @Id
    private String name;

    public Make() {
    }

    public Make(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Make{" +
                "name='" + name + '\'' +
                '}';
    }
}