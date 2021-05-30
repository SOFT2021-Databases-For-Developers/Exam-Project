package app.controllers.rest.mongo;

import app.models.dto.OrderDTO;
import app.models.dto.UserDTO;
import app.models.mongo.Order;
import app.models.mongo.User;
import app.models.postgresql.Listing;
import app.repositories.MongoService;
import app.repositories.PostgresqlService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
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
        if (l.size() != 0) {
            return new ResponseEntity<>(l, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User u = mongoService.getUserById(id);
        if(u != null ) {
            return new ResponseEntity<>(u, HttpStatus.OK);
        }  else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/listings")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Listing>> getListingsForUser(@PathVariable String id) {
        Collection<Listing> l = postgresqlService.getListingsBySeller(id);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/{id}/orders")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<OrderDTO>> getOrdersForUser(@PathVariable String id) {
        Collection<Order> l = mongoService.getOrdersByUser(id);
        Collection<OrderDTO> orderDTOS = new ArrayList<>();
        for(Order o : l) {
            OrderDTO  odto = new OrderDTO();
            odto.setId(o.getId());
            odto.setCreatedAt(o.getCreatedAt());
            odto.setUser(o.getUser());
            List<Listing> fetchedListings = new ArrayList<>();
            for(int listingId : o.getListingIds()) {
                Listing lis = postgresqlService.getListingsById(listingId);
                fetchedListings.add(lis);
            }
            odto.setListings(fetchedListings);
            orderDTOS.add(odto);
        }
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }


    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UserDTO> loginUser(@RequestBody User user) {
        UserDTO foundUser = mongoService.loginUser(user);
        if(foundUser != null) {
            return new ResponseEntity<>(foundUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(mongoService.createUser(user), HttpStatus.CREATED);
    }


}
