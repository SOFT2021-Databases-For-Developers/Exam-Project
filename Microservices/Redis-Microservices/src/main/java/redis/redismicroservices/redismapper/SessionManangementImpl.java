package redis.redismicroservices.redismapper;

import redis.clients.jedis.Jedis;
import redis.redismicroservices.dto.SessionObject;

import java.util.List;

public class SessionManangementImpl implements SessionManagement{
    private Jedis jedis;

    public SessionManangementImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public boolean createSessionForUser(String username, List<String> shoppingCart) {
        try {
            jedis.del(username);
            for(String s : shoppingCart)
            {
                jedis.lpush(username, s);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public SessionObject getSessionForUser(String username) {
        Long length = jedis.llen(username);
        List<String> shoppingCart = jedis.lrange(username, 0, length - 1);
        return new SessionObject(username, shoppingCart);
    }

    @Override
    public boolean deleteSessionForUser(String username) {
        try{
            jedis.del(username);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
