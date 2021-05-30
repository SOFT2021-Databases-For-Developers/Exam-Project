package app.mongo.controllers.order;

import app.mongo.controllers.user.UserController;
import app.mongo.exceptions.NotFoundException;
import app.mongo.models.order.NewOrder;
import app.mongo.repositories.order.OrderService;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@ResponseBody
@RestController
@RequestMapping("/orders")
public class OrderServiceController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OrderService orderService;


    @GetMapping("")
    public ResponseEntity<Collection<NewOrder>> newOrdersGetAll() throws NotFoundException,  MongoException{
        Collection<NewOrder> l = orderService.findAll();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewOrder> retrieveOrderById(@PathVariable String id) throws NotFoundException, MongoException {
        try {
            Optional<NewOrder> order = orderService.findById(id);
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }


    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<NewOrder>> getOrdersForUser(@PathVariable String user) throws NotFoundException,  MongoException {
        try {
            Collection<NewOrder> l = orderService.findAllByUserId(user);
            return new ResponseEntity<>(l, HttpStatus.OK);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<NewOrder> createOrder(@RequestBody NewOrder order) throws NotFoundException, MongoException{
        try {
            if(order.getListingIds().size() > 0 && order.getUser() != null) {
                LOGGER.info("[LOGGER] ::: ORDER CONTROLLER ::: ORDER ::: " + order);
                order.setId(null);
                return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable String id) throws NotFoundException, MongoException {
        try {
            LOGGER.info("[LOGGER] ::: ORDER CONTROLLER ::: ORDER ::: " + id);
            orderService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

}