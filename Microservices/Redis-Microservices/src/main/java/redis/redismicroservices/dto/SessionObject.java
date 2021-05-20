package redis.redismicroservices.dto;

import java.util.List;

public class SessionObject {
    private String username;;
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
