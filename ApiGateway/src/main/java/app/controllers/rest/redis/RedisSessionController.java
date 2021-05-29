package app.controllers.rest.redis;

import app.models.redis.SessionObject;
import app.repositories.RedisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class RedisSessionController {
    private final RedisService redisService;

    public RedisSessionController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/{username}")
    public SessionObject get(@PathVariable String username)
    {
        return redisService.get(username);
    }

    @PostMapping("/")
    public SessionObject add(@RequestBody SessionObject sessionObject)
    {
        return redisService.add(sessionObject);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username)
    {
        redisService.delete(username);
    }

}
