package redis.controllers;

import org.springframework.web.bind.annotation.*;
import redis.models.SessionObject;
import redis.repository.SessionRepository;

@RestController
public class SessionController {
    private SessionRepository SessionRepo;

    public SessionController(SessionRepository sessionRepo) {
        SessionRepo = sessionRepo;
    }

    @GetMapping("/test")
    public SessionObject test()
    {
        return new SessionObject("test", null);
    }

    @GetMapping("/{username}")
    public SessionObject get(@PathVariable String username)
    {
        return SessionRepo.get(username);
    }

    @PostMapping("")
    public SessionObject add(@RequestBody SessionObject sessionObject)
    {
        SessionRepo.save(sessionObject);
        return SessionRepo.get(sessionObject.getUsername());
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username)
    {
        SessionRepo.delete(username);
    }
}
