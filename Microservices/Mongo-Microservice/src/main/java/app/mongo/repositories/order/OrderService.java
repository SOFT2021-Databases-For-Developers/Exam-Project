package app.mongo.repositories.order;


import app.mongo.models.order.NewOrder;
import com.mongodb.MongoException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestResource
public interface OrderService extends MongoRepository<NewOrder, String>
{
    List<NewOrder> findAll() throws MongoException;
    List<NewOrder> findAllByUserId(@PathVariable String id);

}