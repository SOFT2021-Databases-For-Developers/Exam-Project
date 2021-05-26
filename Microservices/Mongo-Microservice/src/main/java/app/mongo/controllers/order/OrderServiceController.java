package app.mongo.controllers.order.OrderServiceController;
import app.mongo.controllers.user.UserController;
import app.mongo.exceptions.NotFoundException;
import app.mongo.models.order.OrderOrder;
import app.mongo.models.order.OrderLine;
import app.mongo.repositories.order.OrderService;
import app.mongo.repositories.order.OrderService;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;





//@RepositoryRestController
//@RepositoryRestResource
@ResponseBody
@RestController
@RequestMapping("/orders")
public class OrderServiceController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OrderService orderRepo;


    @GetMapping("")
    public List<OrderOrder> retrieveAll() {
        return orderRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<OrderOrder> retrieveOrderById(@PathVariable String id) throws NotFoundException, MongoException {
        try {
            return orderRepo.findById(id);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @GetMapping("/id/{id}/orderlines")
    public List<OrderLine> retrieveOrderLinesByOrderId(@PathVariable String id) throws NotFoundException, MongoException {
        try {
            Optional<OrderOrder> _orderOpt = orderRepo.findById(id);
            return _orderOpt.get().getOrderLines();
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @PostMapping("/create")
    public OrderOrder createOrder(@RequestBody OrderOrder order) {
        try {
            LOGGER.info("[LOGGER] ::: ORDER CONTROLLER ::: ORDER ::: " + order);
            return orderRepo.save(order);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable String id) {
        try {
            LOGGER.info("[LOGGER] ::: ORDER CONTROLLER ::: ORDER ::: " + id);
            orderRepo.deleteById(id);
            return "Deleted record of " + id;
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ORDER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }
}