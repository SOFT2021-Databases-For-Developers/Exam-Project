package app.postgresql.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="listings")
public class Listing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "seller")
    private String seller;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float price;
    @Column(name = "km")
    private int km;
    @Column(name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn (name="car_id")
    private Car car;

    @Column(name = "created_on")
    private Date created_on;

    public Listing() {
    }

    public Listing(String seller, String title, String description, float price, int km, Status status, Car car, Date created_on) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
                ", status=" + status +
                ", car=" + car +
                ", created_on=" + created_on +
                '}';
    }
}
