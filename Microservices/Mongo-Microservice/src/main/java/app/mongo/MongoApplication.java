package app.mongo;


import app.mongo.models.dto.UserDTO;
import app.mongo.models.order.*;
import app.mongo.repositories.order.OrderService;
import app.mongo.repositories.user.UserRepository;
import io.github.kaiso.relmongo.config.EnableRelMongo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.ArrayList;
import java.util.List;

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


        UserDTO user = new UserDTO(userRepo.findByEmail("test@test.com"));
        List<Integer> itemList = new ArrayList<>();
        itemList.add(1208651);
        itemList.add(1208652);
        itemList.add(1208653);
        orderService.save(new NewOrder(user, itemList));


            //userRepo.deleteAll();
            //orderRepo.deleteAll();
            //itemRepo.deleteAll();
//
//
//
//        User c1 = new User("Thomas","Ebsen","thomas@hey.dk",Encrypt.hashPassword("root1234"));
//        User c2 = new User("Jonas","Hein","Jonas@hey.dk",Encrypt.hashPassword("root1234"));
//        User c3 = new User("Jonatan","Bakke","Jonatan@hey.dk",Encrypt.hashPassword("root1234"));
//        userRepo.save(c1);
//        userRepo.save(c2);
//        userRepo.save(c3);
//
//        Item i1 = new Item("coolCar1",24);
//        Item i2 = new Item("coolCar2",25);
//        Item i3 = new Item("coolCar3",26);
//        itemRepo.save(i1);
//        itemRepo.save(i2);
//        itemRepo.save(i3);
//
//        List<Item> productList = itemRepo.findAll();
//        OrderLine ld1 = new OrderLine(productList.get(0));
//        OrderLine ld2 = new OrderLine(productList.get(1));
//        OrderLine ld3 = new OrderLine(productList.get(2));
//
//
//        List<OrderLine> orderLines = Arrays.asList(ld1, ld2);
//        List<OrderLine> orderLines2 = Arrays.asList(ld2, ld3);
//        List<OrderLine> orderLines3 = Arrays.asList(ld1, ld2, ld3);
//        User cust1 = userRepo.findByEmail("thomas@hey.dk");
//        User cust2 = userRepo.findByEmail("Jonas@hey.dk");
//        User cust3 = userRepo.findByEmail("Jonatan@hey.dk");
//        OrderOrder order = new OrderOrder(cust1, orderLines);
//        OrderOrder order2 = new OrderOrder( cust2, orderLines2);
//        OrderOrder order3 = new OrderOrder(cust3, orderLines3);
//        orderRepo.save(order);
//        orderRepo.save(order2);
//        orderRepo.save(order3);
//
//        User user = userRepo.findByEmail("thomas@hey.dk");
//
//        System.out.println(">>>>>> " + Encrypt.checkPassword("asdad", user.getPassword()));
//        System.out.println(">>>>>>>>" + userRepo.findByEmail("thomas@hey.dk"));



    }



}


