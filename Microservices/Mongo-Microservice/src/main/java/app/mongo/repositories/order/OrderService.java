package app.mongo.repositories.order;


import app.mongo.models.order.OrderOrder;

import com.mongodb.MongoException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderService extends MongoRepository<OrderOrder, String>
{
    List<OrderOrder> findAll() throws MongoException;

}