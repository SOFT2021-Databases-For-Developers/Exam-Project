package app.mongo.repositories.user;

import app.mongo.models.customer.Customer;
import app.mongo.models.order.Order;
import app.mongo.models.user.User;
import com.mongodb.MongoException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>
{
    List<User> findAll() throws MongoException;
    User findByMail(String mail) throws MongoException;
}