package app.postgresql;

import app.postgresql.models.Listing;
import app.postgresql.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PostgresqlApplication implements CommandLineRunner {

    @Autowired
    private ListingRepository listingRepo;


    public static void main(String[] args) {
        SpringApplication.run(PostgresqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Listing> listings = listingRepo.findAll();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");
        for (Listing l : listings) {
            System.out.println(l);
        }
    }
}
