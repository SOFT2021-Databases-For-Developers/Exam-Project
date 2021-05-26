package redis.redismicroservices.redismapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import redis.clients.jedis.Jedis;
import redis.redismicroservices.dto.SessionObject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SessionManagementTest {

    private GenericContainer redisContainer;
    protected SessionManagement sessionManagement;
    private Jedis jedis;
    private String host = "localhost";
    private int port = 6379;

    private void setupContainer() {
        redisContainer = new GenericContainer(DockerImageName.parse("redis:alpine"))
                .withExposedPorts(6379);
        redisContainer.start();
        host = redisContainer.getHost();
        port = redisContainer.getFirstMappedPort();
    }

    @BeforeAll
    public void setup(){
        host = "localhost";
        port = 6379;

        /*
        The following line starts a new container with redis, and runs integration tests on it.

        Depending on your setup, it might not work. If it doesn't work, try this in your terminal:
        docker pull testcontainers/ryuk:0.3.0

        If it still doesn't work, you can comment out the line,
        BUT then the integration tests will be run against your local redis, AND IT WILL FLUSH DB 9!
        To prove that you have read this warning, delete the exception below.
        */
        setupContainer();

        jedis = new Jedis(host, port);
        jedis.select(9);

        sessionManagement = new SessionManangementImpl(jedis);
    }

    @BeforeEach
    public void beforeEach() {
        jedis.flushDB();
    }

    @Test
    void createSessionForUser() {
        String username = "Jonatan";
        List<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Item 1");
        shoppingCart.add("Item 1");
        boolean result = sessionManagement.createSessionForUser(username, shoppingCart);
        assertTrue(result);
    }

    @Test
    void getSessionForUser() {
        String username = "Jonatan";
        List<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Item 1");
        shoppingCart.add("Item 1");
        boolean createSessionResult = sessionManagement.createSessionForUser(username, shoppingCart);
        assertTrue(createSessionResult);
        SessionObject result = sessionManagement.getSessionForUser(username);
        assertEquals(result.getShoppingCart().size(), 2);
    }

    @Test
    void deleteSessionForUser() {
        String username = "Jonatan";
        List<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Item 1");
        shoppingCart.add("Item 2");
        boolean createSession = sessionManagement.createSessionForUser(username, shoppingCart);
        assertTrue(createSession);
        boolean deleteSession = sessionManagement.deleteSessionForUser(username);
        assertTrue(deleteSession);
        SessionObject session = sessionManagement.getSessionForUser(username);
        assertEquals(session.getShoppingCart().size(), 0);
    }

    @Test
    void overwriteUserSession() {
        String username = "Jonatan";
        List<String> shoppingCart = new ArrayList<>();
        shoppingCart.add("Item 1");
        shoppingCart.add("Item 1");
        boolean createSessionResult = sessionManagement.createSessionForUser(username, shoppingCart);
        assertTrue(createSessionResult);
        SessionObject result = sessionManagement.getSessionForUser(username);
        assertEquals(result.getShoppingCart().size(), 2);

        shoppingCart.add("Item 3");
        createSessionResult = sessionManagement.createSessionForUser(username, shoppingCart);
        assertTrue(createSessionResult);
        result = sessionManagement.getSessionForUser(username);
        assertEquals(result.getShoppingCart().size(), 3);

    }
}