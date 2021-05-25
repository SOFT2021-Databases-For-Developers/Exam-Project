package app.postgresql;

import app.postgresql.helpers.JsonReader;
import app.postgresql.helpers.Generator;
import app.postgresql.models.Car;
import app.postgresql.models.Listing;
import app.postgresql.models.Model;
import app.postgresql.repositories.car.CarRepository;
import app.postgresql.repositories.listing.ListingRepository;
import app.postgresql.repositories.make.MakeRepository;
import app.postgresql.repositories.model.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class PostgresqlApplication implements CommandLineRunner {


    @Autowired
    private MakeRepository makeRepo;
    private ListingRepository listingRepo;
    private ModelRepository modelRepo;
    private CarRepository carRepository;

    PostgresqlApplication(MakeRepository makeRepo, ListingRepository listingRepo, ModelRepository modelRepo, CarRepository carRepository) {
        this.makeRepo = makeRepo;
        this.listingRepo = listingRepo;
        this.modelRepo = modelRepo;
        this.carRepository = carRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(PostgresqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { ;
        long startTime = System.currentTimeMillis();
        //PopulateDB(true, 10, true);
        //listingRepo.saveAll(GenerateFakeListings(true, 1000));

        int count = 0;
        for (Listing l : listingRepo.findAll()) {
            count++;
            System.out.print(count + " ");
            System.out.println(l);
        }

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.



        System.out.println("Duration: " + duration/1000 + " seconds.");

        //listingRepo.save(new Listing("AJSDAR1231234JJ", "New", "New", 100, 871907,  99, new Date()));


    }

    public List<Listing> GenerateFakeListings(boolean run, int amount) {
        List<Listing> listings = new ArrayList<>();
        if (run) {
            List<Car> cars = carRepository.findAll();
            Random random = new Random();
            for (int i = 0; i < amount; i++) {
                int rndIndex = (int)(Math.random() * cars.size());
                Car c = cars.get(rndIndex);
                Listing l = new Listing();
                l.setSeller_id(Generator.GenerateRandomAlphanumericString(24));
                l.setCar_id(c.getId());
                l.setPrice(random.nextInt(99999));
                l.setCreated_on(new Date());
                l.setKm(random.nextInt(99999));
                l.setDescription(Generator.GenerateListingDescription(c.getMake(), c.getModel(), c.getCategory(), c.getYear(), l.getKm()));
                l.setTitle(Generator.GenerateListingTitle(c.getMake(), c.getModel(), c.getCategory(), c.getYear(), l.getKm()));
                listings.add(l);
            }
        }
        return listings;
    }

    private void PopulateDB(boolean run, int amount_to_add, boolean delete_first) {
        if(delete_first == true) {
            listingRepo.deleteAll();
            carRepository.deleteAll();
            modelRepo.deleteAll();
            makeRepo.deleteAll();
        }
        if(run == true) {
            makeRepo.saveAll(JsonReader.getMakesFromJson());
            modelRepo.saveAll(JsonReader.getModelsAndMakesFromJson());
            carRepository.saveAll(JsonReader.getCarsFromJson());
            listingRepo.saveAll(GenerateFakeListings(true, amount_to_add));
        }


    }
}
