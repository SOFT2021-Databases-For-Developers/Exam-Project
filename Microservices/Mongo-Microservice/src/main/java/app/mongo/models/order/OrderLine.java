package app.mongo.models.order;



import app.mongo.models.item.Item;

import io.github.kaiso.relmongo.annotation.CascadeType;
import io.github.kaiso.relmongo.annotation.FetchType;
import io.github.kaiso.relmongo.annotation.JoinProperty;
import io.github.kaiso.relmongo.annotation.OneToOne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Document(collection ="orderlines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @OneToOne(fetch= FetchType.EAGER, cascade = CascadeType.NONE)
    @JoinProperty(name = "item")
    private Item item;

    public OrderLine() {
    }

    public OrderLine(String id, Item item) {
        this.id = id;
        this.item = item;
    }

    public OrderLine(Item item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }





    @Override
    public String toString() {
        return "OrderLine{" +
                "id='" + id + '\'' +
                ", item=" + item +
                '}';
    }
}
