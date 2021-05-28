package app.mongo;


import app.mongo.helpers.Encrypt;

import app.mongo.models.item.Item;
import app.mongo.models.order.OrderLine;
import app.mongo.models.order.OrderOrder;
import app.mongo.models.user.User;

import app.mongo.repositories.item.ItemService;
import app.mongo.repositories.order.OrderService;
import app.mongo.repositories.user.UserRepository;
import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRelMongo
public class MongoApplication implements CommandLineRunner {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(String.valueOf(MongoApplication.class));


    @Autowired
    UserRepository userRepo;
    ItemService itemRepo;
    OrderService orderRepo;

    MongoApplication(UserRepository userRepo, ItemService itemRepo, OrderService orderRepo) {
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.orderRepo = orderRepo;
    }

    public static void main(String[] args){SpringApplication.run(MongoApplication.class, args); }

    @Override
    public void run(String ...args) throws Exception{

        userRepo.deleteAll();
        orderRepo.deleteAll();
        itemRepo.deleteAll();



        User c1 = new User("Thomas","Ebsen","thomas@hey.dk",Encrypt.hashPassword("root1234"));
        User c2 = new User("Jonas","Hein","Jonas@hey.dk",Encrypt.hashPassword("root1234"));
        User c3 = new User("Jonatan","Bakke","Jonatan@hey.dk",Encrypt.hashPassword("root1234"));
        userRepo.save(c1);
        userRepo.save(c2);
        userRepo.save(c3);

        Item i1 = new Item("coolCar1",24);
        Item i2 = new Item("coolCar2",25);
        Item i3 = new Item("coolCar3",26);
        itemRepo.save(i1);
        itemRepo.save(i2);
        itemRepo.save(i3);

        List<Item> productList = itemRepo.findAll();
        OrderLine ld1 = new OrderLine(productList.get(0));
        OrderLine ld2 = new OrderLine(productList.get(1));
        OrderLine ld3 = new OrderLine(productList.get(2));


        List<OrderLine> orderLines = Arrays.asList(ld1, ld2);
        List<OrderLine> orderLines2 = Arrays.asList(ld2, ld3);
        List<OrderLine> orderLines3 = Arrays.asList(ld1, ld2, ld3);
        User cust1 = userRepo.findByMail("thomas@hey.dk");
        User cust2 = userRepo.findByMail("Jonas@hey.dk");
        User cust3 = userRepo.findByMail("Jonatan@hey.dk");
        OrderOrder order = new OrderOrder(cust1, orderLines);
        OrderOrder order2 = new OrderOrder( cust2, orderLines2);
        OrderOrder order3 = new OrderOrder(cust3, orderLines3);
        orderRepo.save(order);
        orderRepo.save(order2);
        orderRepo.save(order3);



    }



}


