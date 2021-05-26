package app.postgresql.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "makes")
public class Make implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;

    public Make() {
    }

    public Make(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MakeNew{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
