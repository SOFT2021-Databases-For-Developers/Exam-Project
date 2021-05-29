package app.mongo.controllers.user;


import app.mongo.exceptions.NotFoundException;

import app.mongo.helpers.Encrypt;
import app.mongo.models.dto.UserDTO;
import app.mongo.models.user.User;
import app.mongo.repositories.user.UserRepository;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ResponseBody
@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserRepository repo;

    @GetMapping("")
    public List<User> findAll(){
        return repo.findAll();
    }

    @GetMapping("/mail/{mail}")
    public User findByMail(@PathVariable String mail) throws NotFoundException, MongoException {
        try {
            return repo.findByEmail(mail);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: USER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @GetMapping("/id/{id}")
    public Optional<User> findById(@PathVariable String id) throws NotFoundException, MongoException {
        try {
            return repo.findById(id);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: CUSTOMER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());
            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        try {
            LOGGER.info("[LOGGER] ::: USER CONTROLLER ::: CREATED USER ::: " + user);
            User _user = user;
            _user.setPassword(Encrypt.hashPassword(user.getPassword()));
            return repo.save(_user);
        } catch (MongoException ex) {
            LOGGER.error("[LOGGER] ::: USER CONTROLLER ::: " + ex.getCode() + " ::: " + ex.getMessage());

            throw new NotFoundException(ex.getCode() + " : " + ex.getMessage());
        }
    }

    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody User user) {
        User foundUser = repo.findByEmail(user.getEmail());
        boolean isValidated = Encrypt.checkPassword(user.getPassword(), foundUser.getPassword());
        if (isValidated) {
            UserDTO u = new UserDTO(foundUser);
            return u;
        } else {
            return null;
        }
    }



}