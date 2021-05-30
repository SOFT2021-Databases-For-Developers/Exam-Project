package app.models.dto;

import app.models.mongo.Order;
import app.models.mongo.User;
import app.models.postgresql.Listing;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String id;
    private Date createdAt;
    private UserDTO user;
    private List<Listing> listings;

    public OrderDTO() {
    }

    public OrderDTO(UserDTO user, List<Listing> listings) {
        this.user = user;
        this.listings = listings;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", listings=" + listings +
                '}';
    }
}
