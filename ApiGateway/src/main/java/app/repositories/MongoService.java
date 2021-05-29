package app.repositories;

import app.config.RibbonConfig;
import app.models.dto.UserDTO;
import app.models.mongo.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@FeignClient("mongo-service")
@RibbonClient(name = "mongo-service", configuration = RibbonConfig.class)
public interface MongoService {
    /* USER */
    @GetMapping("/users")
    Collection<User> getUsers();
    @PostMapping("/users")
    User createUser();
    @GetMapping("/users/id/{id}")
    User getUserById(@PathVariable String id);
    @PostMapping("/users/login")
    UserDTO loginUser(@RequestBody User user);
}
