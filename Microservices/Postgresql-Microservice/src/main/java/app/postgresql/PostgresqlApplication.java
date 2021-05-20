package app.postgresql;

import app.postgresql.models.Listing;
import app.postgresql.models.Make;
import app.postgresql.repositories.listing.ListingRepository;
import app.postgresql.repositories.make.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PostgresqlApplication implements CommandLineRunner {

    @Autowired
    private MakeRepository makeRepo;
    private ListingRepository listingRepo;

    PostgresqlApplication(MakeRepository makeRepo, ListingRepository listingRepo) {
        this.makeRepo = makeRepo;
        this.listingRepo = listingRepo;
    }


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

        List<Make> makes = makeRepo.findAll();
        for (Make m : makes) {
            System.out.println(m);
        }

        List<Listing> lis = listingRepo.findAllByMakeAndModel("Volvo", "X1");
        for(Listing x : lis) {
            System.out.println(x);
        }

        makeRepo.save(new Make("Test"));
    }
}
