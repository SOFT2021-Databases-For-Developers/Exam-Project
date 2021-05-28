package app.models.postgresql;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Car implements Serializable {
    @Id
    private int id;
    private Make make;
    private Model model;

    public Car() {
    }

    public Car(Make make, Model model) {
        this.make = make;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make=" + make +
                ", model=" + model +
                '}';
    }
}
