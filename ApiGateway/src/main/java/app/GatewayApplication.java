package app;

import app.repositories.MongoService;
import app.repositories.PostgresqlService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrixDashboard
@EnableFeignClients
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
// Feign enables dynamic processing of annotations
public class GatewayApplication implements CommandLineRunner {

    private final MongoService mongoService;
    private final PostgresqlService postgresqlService;


    public GatewayApplication(MongoService mongoService, PostgresqlService postgresqlService) {
        this.mongoService = mongoService;
        this.postgresqlService = postgresqlService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {




    }
}



