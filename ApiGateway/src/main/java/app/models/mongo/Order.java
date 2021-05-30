package app.models.mongo;

import app.models.dto.UserDTO;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Order implements Serializable {
    @Id
    private String id;
    private Date createdAt;
    private UserDTO user;
    private List<Integer> listingIds;

    public Order() {
    }

    public Order(UserDTO user, List<Integer> listingIds) {
        this.user = user;
        this.listingIds = listingIds;
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
        return "Order{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", listingIds=" + listingIds +
                '}';
    }
}
