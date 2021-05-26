package redis.redismicroservices.redismapper;

import org.springframework.data.repository.CrudRepository;
import redis.redismicroservices.dto.SessionObject;

import java.util.List;


public interface SessionManagement {
    boolean createSessionForUser(String username, List<String> shoppingCart);
    SessionObject getSessionForUser(String username);
    boolean deleteSessionForUser(String username);
}
