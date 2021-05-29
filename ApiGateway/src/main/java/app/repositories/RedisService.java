package app.repositories;

import app.config.RibbonConfig;
import app.models.redis.SessionObject;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@FeignClient("redis-service")
@RibbonClient(name = "redis-service", configuration = RibbonConfig.class)
public interface RedisService {
    @GetMapping("/{username}")
    SessionObject get(@PathVariable String username);

    @PostMapping("/")
    SessionObject add(@RequestBody SessionObject sessionObject);

    @DeleteMapping("/{username}")
    void delete(@PathVariable String username);
}
