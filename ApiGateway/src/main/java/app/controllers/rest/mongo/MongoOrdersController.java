package app.controllers.rest.mongo;

import app.models.dto.OrderDTO;
import app.models.mongo.Order;
import app.models.postgresql.Listing;
import app.models.postgresql.Status;
import app.repositories.MongoService;
import app.repositories.PostgresqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MongoOrdersController {

    private final MongoService mongoService;
    private final PostgresqlService postgresqlService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoOrdersController.class);

    public MongoOrdersController(MongoService mongoService, PostgresqlService postgresqlService) {
        this.mongoService = mongoService;
        this.postgresqlService = postgresqlService;
    }

    @GetMapping("")
    public ResponseEntity<Collection<OrderDTO>> getOrders() {
        Collection<Order> l = mongoService.getOrders();
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

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> retrieveOrderById(@PathVariable String id) {
            Order order = mongoService.getOrderById(id);
            OrderDTO  odto = new OrderDTO();
            odto.setId(order.getId());
            odto.setCreatedAt(order.getCreatedAt());
            odto.setUser(order.getUser());
            List<Listing> fetchedListings = new ArrayList<>();
            for(int listingId : order.getListingIds()) {
                Listing lis = postgresqlService.getListingsById(listingId);
                fetchedListings.add(lis);
            }
            odto.setListings(fetchedListings);
            return new ResponseEntity<>(odto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
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

    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        if(order.getListingIds().size() > 0 && order.getUser() != null) {
            for (int id : order.getListingIds()) {
                postgresqlService.updateListingStatus(id, Status.SOLD);
            }
            return new ResponseEntity<>(mongoService.createOrder(order), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable String id) {
            mongoService.deleteOrderById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
