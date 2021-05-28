package app.models.postgresql;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Make implements Serializable {
    @Id
    private int id;
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
        return "Make{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
