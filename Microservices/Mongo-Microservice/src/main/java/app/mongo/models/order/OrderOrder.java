package app.mongo.models.order;


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
public class OrderOrder {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Date createdAt;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinProperty(name = "users")
    private User user;
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinProperty(name = "orderlines")
    private List<OrderLine> orderLines;

    public OrderOrder() {
    }

    public OrderOrder(String id, Date createdAt, User user, List<OrderLine> orderLines) {
        this.id = id;
        this.createdAt = createdAt;
        this.user = user;
        this.orderLines = orderLines;
    }

    public OrderOrder( User user, List<OrderLine> orderLines) {
        this.createdAt = createdAt;
        this.user = user;
        this.orderLines = orderLines;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", orderLines=" + orderLines +
                '}';
    }
}
