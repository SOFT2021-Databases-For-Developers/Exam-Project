package app.mongo.controllers.user;

import app.mongo.models.user.User;
import app.mongo.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ResponseBody
@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    UserRepository repo;

    @GetMapping("")
    public List<User> findAll(){
        return repo.findAll();
    }
}