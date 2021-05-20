package app.postgresql.models;
import lombok.Data;
import lombok.Generated;

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
    @Column(name = "make_name")
    private String make_name;
    @Column(name = "model_name")
    private String model_name;
    @Column(name = "model_year")
    private int model_year;
    @Column(name = "created_on")
    private Date created_on;

    public Listing() {
    }

    public Listing(String seller_id, String title, String description, float price, String make_name, String model_name, int model_year) {
        this.seller_id = seller_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.make_name = make_name;
        this.model_name = model_name;
        this.model_year = model_year;
    }

    public int getId() {
        return id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMake_name() {
        return make_name;
    }

    public void setMake_name(String make_name) {
        this.make_name = make_name;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public int getModel_year() {
        return model_year;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public Date getCreated_on() {
        return created_on;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", seller_id='" + seller_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", make_name='" + make_name + '\'' +
                ", model_name='" + model_name + '\'' +
                ", model_year=" + model_year +
                ", created_on=" + created_on +
                '}';
    }
}