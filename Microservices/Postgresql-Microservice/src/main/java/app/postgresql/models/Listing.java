package app.postgresql.models;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "listing")

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "listing.findAllByMakeAndModel",
                procedureName = "FIND_ALL_LISTINGS_BY_MAKE_AND_MODEL", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "_make", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "_model", type = String.class)})
})

public class Listing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "seller_id")
    private String seller_id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private float price;
    @Column(name = "km")
    private int km;
    @Column(name = "car_id")
    private int car_id;
    @Column(name = "created_on")
    private Date created_on;

    public Listing() {
    }

    public Listing(String seller_id, String title, String description, int km, int car_id, float price, Date created_on) {
        this.seller_id = seller_id;
        this.title = title;
        this.description = description;
        this.km = km;
        this.car_id = car_id;
        this.price = price;
        this.created_on = created_on;
    }

    public Listing(int id, String seller_id, String title, String description, int km, int car_id, float price, Date created_on) {
        this.id = id;
        this.seller_id = seller_id;
        this.title = title;
        this.description = description;
        this.km = km;
        this.car_id = car_id;
        this.price = price;
        this.created_on = created_on;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", seller_id='" + seller_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", km=" + km +
                ", car_id=" + car_id +
                ", price=" + price +
                ", created_on=" + created_on +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
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

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }
}