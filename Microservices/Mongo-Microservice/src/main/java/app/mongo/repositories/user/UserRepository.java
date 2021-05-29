package app.mongo.repositories.user;

import app.mongo.models.user.User;
import com.mongodb.MongoException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>
{
    List<User> findAll() throws MongoException;
    User findByEmail(String mail) throws MongoException;
    Optional<User> findById(String id) throws MongoException;
}