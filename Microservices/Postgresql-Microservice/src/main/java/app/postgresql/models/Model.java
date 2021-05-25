package app.postgresql.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "model")
public class Model implements Serializable {
    @Id
    private String name;
    @Column(name = "make_name")
    private String make_name;

    public Model() {
    }

    public Model(String name, String make_name) {
        this.name = name;
        this.make_name = make_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake_name() {
        return make_name;
    }

    public void setMake_name(String make_name) {
        this.make_name = make_name;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", make_name='" + make_name + '\'' +
                '}';
    }
}
