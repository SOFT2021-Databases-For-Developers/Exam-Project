package app.postgresql.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "models")
public class Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int year;

    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;

    public Model() {
    }

    public Model(String name, int year, Make make) {
        this.name = name;
        this.year = year;
        this.make = make;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", make=" + make +
                '}';
    }
}
