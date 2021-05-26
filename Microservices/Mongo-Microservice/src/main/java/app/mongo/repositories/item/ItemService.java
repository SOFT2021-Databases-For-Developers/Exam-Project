package app.mongo.repositories.item;

import app.mongo.models.item.Item;
import com.mongodb.MongoException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ItemService extends MongoRepository<Item, String>
{
    List<Item> findAll() throws MongoException;
    Item findByTitle(String title) throws MongoException;
}