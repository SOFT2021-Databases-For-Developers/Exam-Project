package app.mongo.models.order;

import app.mongo.models.dto.UserDTO;
import app.mongo.models.user.User;
import io.github.kaiso.relmongo.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;

@Data
@Document(collection ="orders")
public class NewOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Date createdAt;
    private UserDTO user;
    private List<Integer> listingIds;

    public NewOrder() {
    }

    public NewOrder(UserDTO user, List<Integer> listingIds) {
        this.user = user;
        this.listingIds = listingIds;
        this.createdAt = new Date();
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

    public List<Integer> getListingIds() {
        return listingIds;
    }

    public void setListingIds(List<Integer> listingIds) {
        this.listingIds = listingIds;
    }

    @Override
    public String toString() {
        return "NewOrder{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", listingIds=" + listingIds +
                '}';
    }
}
