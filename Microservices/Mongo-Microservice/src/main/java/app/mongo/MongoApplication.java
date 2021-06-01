package app.mongo;


import app.mongo.helpers.Encrypt;
import app.mongo.models.user.User;
import app.mongo.repositories.order.OrderService;
import app.mongo.repositories.user.UserRepository;
import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRelMongo
public class MongoApplication implements CommandLineRunner {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(String.valueOf(MongoApplication.class));



    private final UserRepository userRepo;
    private final OrderService orderService;

    public MongoApplication(UserRepository userRepo, OrderService orderService) {
        this.userRepo = userRepo;
        this.orderService = orderService;
    }

    public static void main(String[] args){SpringApplication.run(MongoApplication.class, args); }

    @Override
    public void run(String ...args) throws Exception{

        orderService.deleteAll();
        userRepo.deleteAll();


        userRepo.save(new User("Thomas", "E", "thomas@root.com", Encrypt.hashPassword("root")));
        userRepo.save(new User("Jonas", "E", "jonas@root.com", Encrypt.hashPassword("root")));
        userRepo.save(new User("Andreas", "E", "andreas@root.com", Encrypt.hashPassword("root")));
        userRepo.save(new User("Jonatan", "E", "jonatan@root.com", Encrypt.hashPassword("root")));


    }



}


