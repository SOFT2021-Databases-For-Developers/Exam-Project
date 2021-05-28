package app.controllers.rest.mongo;

import app.models.mongo.User;
import app.models.postgresql.Listing;
import app.repositories.MongoService;
import app.repositories.PostgresqlService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class MongoUsersController {
    private final MongoService mongoService;
    private final PostgresqlService postgresqlService;

    public MongoUsersController(MongoService mongoService, PostgresqlService postgresqlService) {
        this.mongoService = mongoService;
        this.postgresqlService = postgresqlService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<User>> getUsers() {
        Collection<User> l = mongoService.getUsers();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User u = mongoService.getUserById(id);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/{id}/listings")
    public ResponseEntity<Collection<Listing>> getListingsForUser(@PathVariable String id) {
        Collection<Listing> l = postgresqlService.getListingsBySeller(id);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }
}
