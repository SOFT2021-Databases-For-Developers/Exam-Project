package redis.models;

import java.io.Serializable;
import java.util.List;

public class SessionObject implements Serializable {
    private String username;
    private List<String> shoppingCart;

    public SessionObject(String username, List<String> shoppingCart) {
        this.username = username;
        this.shoppingCart = shoppingCart;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getShoppingCart() {
        return shoppingCart;
    }
}
