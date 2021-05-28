package app.models.postgresql;

import javax.validation.constraints.NotNull;

public class Model {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private int year;
    @NotNull
    private Make make;

    public Model() {
    }

    public Model(@NotNull String name, @NotNull int year, @NotNull Make make) {
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
