package app;

import app.helpers.Generator;
import app.models.mongo.User;
import app.models.postgresql.Car;
import app.models.postgresql.Listing;
import app.repositories.MongoService;
import app.repositories.PostgresqlService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.ArrayList;
import java.util.List;

@EnableHystrixDashboard
@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
// Feign enables dynamic processing of annotations
public class GatewayApplication implements CommandLineRunner {

    @Autowired
    private MongoService mongoService;

    @Autowired
    private PostgresqlService postgresqlService;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {



        //User u = new User("Test", "Test", "test@test.com", "1234");
        //mongoService.createUser(u);



    }
}



