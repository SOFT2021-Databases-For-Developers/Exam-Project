package app.mongo;

import app.mongo.controllers.game.GameApiFetcher;
import app.mongo.helpers.Encrypt;
import app.mongo.models.customer.Customer;
import app.mongo.models.game.Game;
import app.mongo.models.order.Order;
import app.mongo.models.order.OrderLine;
import app.mongo.models.order.Status;
import app.mongo.models.user.User;
import app.mongo.repositories.customer.CustomerService;
import app.mongo.repositories.game.GameService;
import app.mongo.repositories.order.OrderLineService;
import app.mongo.repositories.order.OrderService;
import app.mongo.repositories.user.UserRepository;
import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRelMongo
public class MongoApplication implements CommandLineRunner {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(String.valueOf(MongoApplication.class));


    @Autowired
    UserRepository repo;

    MongoApplication(UserRepository repo){
        this.repo = repo;
    };

    public static void main(String[] args){SpringApplication.run(MongoApplication.class, args); }

    @Override
    public void run(String ...args) throws Exception{
        //for (User u : repo.findall())
            //System.out.println(u);
        User user = new User("Thomas","Thomas","Ebsen","thomas@ebsen.dk",Encrypt.hashPassword("root1234"));
        repo.save(user);
    }



}


