package app.mongo.models.userOrder;


import app.mongo.models.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.kaiso.relmongo.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;

@Data
@Document(collection ="userorders")
public class UserOrder {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Date createdAt;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinProperty(name = "users")
    private User user;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinProperty(name = "userorderlines")
    private List<UserOrderLine> orderLines;

    public UserOrder() {
    }

    public UserOrder(String id, Date createdAt, User user, List<UserOrderLine> orderLines) {
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
        this.orderLines = orderLines;
    }

    public UserOrder(Date createdAt, User user, List<UserOrderLine> orderLines) {
        this.createdAt = createdAt;
        this.user = user;
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", orderLines=" + orderLines +
                '}';
    }
}
