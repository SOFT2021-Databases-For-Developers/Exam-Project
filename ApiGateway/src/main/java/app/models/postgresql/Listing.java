package app.models.postgresql;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

public class Listing implements Serializable {
    @Id
    private int id;
    private String seller;
    private String title;
    private String description;
    private float price;
    private int km;
    private Car car;
    private Status status;
    private Date created_on;

    public Listing() {
    }

    public Listing(String seller, String title, String description, float price, int km, Car car, Status status, Date created_on) {
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.price = price;
        this.km = km;
        this.status = status;
        this.car = car;
        this.created_on = created_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", seller='" + seller + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", km=" + km +
                ", car=" + car +
                ", status=" + status +
                ", created_on=" + created_on +
                '}';
    }
}
