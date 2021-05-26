package redis.repository;

import redis.models.SessionObject;

import java.util.Map;

public interface SessionRepository {
    void save(SessionObject session);
    SessionObject get(String username);
    void delete(String username);
}
