package app.repositories;

import app.config.RibbonConfig;
import app.models.dto.OrderDTO;
import app.models.dto.UserDTO;
import app.models.mongo.Order;
import app.models.mongo.User;
import app.models.postgresql.Listing;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@FeignClient("mongo-service")
@RibbonClient(name = "mongo-service", configuration = RibbonConfig.class)
public interface MongoService {
    /* USER */
    @GetMapping("/users")
    Collection<User> getUsers();
    @PostMapping("/users")
    User createUser(@RequestBody User user);
    @GetMapping("/users/id/{id}")
    User getUserById(@PathVariable String id);
    @PostMapping("/users/login")
    UserDTO loginUser(@RequestBody User user);
    @PostMapping("/users/list")
    Collection<User> createUsersFromList(@RequestBody List<User> users);

    /* ORDER */
    @GetMapping("/orders")
    Collection<Order> getOrders();
    @GetMapping("/orders/{id}")
    Order getOrderById(@PathVariable String id);
    @GetMapping("/orders/user/{user}")
    Collection<Order> getOrdersByUser(@PathVariable String user);
    @PostMapping("/orders")
    Order createOrder(@RequestBody Order order);
    @DeleteMapping("/orders")
    Order deleteOrderById(@PathVariable String id);
}
