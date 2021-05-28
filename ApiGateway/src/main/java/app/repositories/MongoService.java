package app.repositories;

import app.config.RibbonConfig;
import app.models.mongo.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@CrossOrigin
@FeignClient("mongo-service")
@RibbonClient(name = "mongo-service", configuration = RibbonConfig.class)
public interface MongoService {
    /* CUSTOMER */
    @GetMapping("/users")
    Collection<User> getUsers();
    @PostMapping("/users")
    User createUser();
    @GetMapping("/users/id/{id}")
    User getUserById(@PathVariable String id);
}
