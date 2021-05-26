package redis.redismicroservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.redismicroservices.dto.SessionObject;
import redis.redismicroservices.redismapper.SessionManagement;


@RestController
public class SessionController {

    @GetMapping("/test")
    public SessionObject testSession()
    {
        return new SessionObject("test Username", null);
    }
}
