package app.postgresql;

import app.postgresql.helpers.Generator;
import app.postgresql.helpers.JsonReader;
import app.postgresql.models.*;
import app.postgresql.repositories.CarRepository;
import app.postgresql.repositories.ListingRepository;
import app.postgresql.repositories.MakeRepository;
import app.postgresql.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.*;

@EnableDiscoveryClient
@SpringBootApplication
public class PostgresqlApplication implements CommandLineRunner {

    @Autowired
    private ListingRepository listingRepository;
    private CarRepository carRepository;
    private ModelRepository modelRepository;
    private MakeRepository makeRepository;

    public PostgresqlApplication(ListingRepository listingRepository, CarRepository carRepository, ModelRepository modelRepository, MakeRepository makeRepository) {
        this.listingRepository = listingRepository;
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.makeRepository = makeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PostgresqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { ;
        long startTime = System.currentTimeMillis();
        System.out.println("Car count: " + carRepository.findAll().size());

        Car c = carRepository.findById(14940).get();
        Listing l = new Listing("TEST-SELLER","Test listing","Test listing",1234,1234, Status.ACTIVE, c, new Date());

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Alright we good!");
        System.out.println("Duration: " + duration/1000 + " seconds.");

    }


    public Collection<Listing> GenerateFakeListings(boolean run, int amount) {
        List<Listing> listings = new ArrayList<>();
        if (run) {
            List<Car> cars = (List<Car>) carRepository.findAll();
            Random random = new Random();
            for (int i = 0; i < amount; i++) {
                int rndIndex = (int)(Math.random() * cars.size());
                Car c = cars.get(rndIndex);

                Listing l = new Listing();
                l.setSeller(Generator.GenerateRandomAlphanumericString(24));
                l.setCar(c);
                l.setPrice(random.nextFloat() * (100 + 100000));
                l.setCreated_on(new Date());
                l.setKm(random.nextInt(99999));
                l.setDescription(Generator.GenerateListingDescription(c.getMake().getName(), c.getModel().getName(), c.getModel().getYear(), l.getKm()));
                l.setTitle(Generator.GenerateListingTitle(c.getMake().getName(), c.getModel().getName(), c.getModel().getYear(), l.getKm()));
                listings.add(l);
            }
        }
        return listings;
    }
//
//    private void PopulateDB(boolean run, int amount_to_add, boolean delete_first) {
//        if(delete_first == true) {
//            listingRepo.deleteAll();
//            carRepository.deleteAll();
//            modelRepo.deleteAll();
//            makeRepo.deleteAll();
//        }
//        if(run == true) {
//            makeRepo.saveAll(JsonReader.getMakesFromJson());
//            modelRepo.saveAll(JsonReader.getModelsAndMakesFromJson());
//            carRepository.saveAll(JsonReader.getCarsFromJson());
//            listingRepo.saveAll(GenerateFakeListings(true, amount_to_add));
//        }
//    }
}
