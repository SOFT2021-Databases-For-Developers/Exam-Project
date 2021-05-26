package app.mongo.controllers.item;



import app.mongo.exceptions.NotFoundException;
import app.mongo.models.item.Item;
import app.mongo.repositories.item.ItemService;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RepositoryRestController
//@RepositoryRestResource
@ResponseBody
@RestController
@RequestMapping("/items")
public class ItemServiceController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(app.mongo.controllers.item.ItemServiceController.class);

    @Autowired
    private ItemService repo;

    @GetMapping("")
    public List<Item> retrieveAll() {
        return repo.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Item> retrieveProductById(@PathVariable String id) throws NotFoundException, MongoException {
        Optional<Item> item;
        try {
            item = repo.findById(id);
            if(item.isPresent()) {
                return item;
            } else {
                LOGGER.error("[LOGGER] ::: ITEM CONTROLLER ::: item Not found ::: id: " + id);
                throw new NotFoundException("ITEM not found");
            }
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ITEM CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @GetMapping("/title/{title}")
    public Item retrieveProductByTitle(@PathVariable String title) throws NotFoundException, MongoException {
        try {
            return repo.findByTitle(title);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ITEM CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @PostMapping("/create")
    public Item createProduct(@RequestBody Item item) {
        try {
            return repo.save(item);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ITEM CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteItem(@PathVariable String id) {
        try {
            repo.deleteById(id);
            return "Deleted record of " + id;
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: ITEM CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }
}
