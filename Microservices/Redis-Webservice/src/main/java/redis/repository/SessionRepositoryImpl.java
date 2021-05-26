package redis.repository;

import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.models.SessionObject;

@Repository
public class SessionRepositoryImpl implements SessionRepository{

    private RedisTemplate<String, SessionObject> redisTemplate;
    private HashOperations hashOperations;

    public SessionRepositoryImpl(RedisTemplate<String, SessionObject> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(SessionObject session) {
        try {
            hashOperations.put("SESSION", session.getUsername(), session);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public SessionObject get(String username) {
        return (SessionObject)hashOperations.get("SESSION", username);
    }

    @Override
    public void delete(String username) {
        hashOperations.delete("SESSION", username);
    }
}
