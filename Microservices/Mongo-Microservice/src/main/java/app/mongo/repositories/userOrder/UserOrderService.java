package app.mongo.repositories.userOrder;


import app.mongo.models.userOrder.UserOrder;
import com.mongodb.MongoException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UserOrderService extends MongoRepository<UserOrder, String>
{
    List<UserOrder> findAll() throws MongoException;
}