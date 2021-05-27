package app.postgresql;

import app.postgresql.helpers.Generator;
import app.postgresql.helpers.JsonReader;
import app.postgresql.models.Car;
import app.postgresql.models.Listing;
import app.postgresql.models.Make;
import app.postgresql.models.Model;
import app.postgresql.repositories.CarRepository;
import app.postgresql.repositories.ListingRepository;
import app.postgresql.repositories.MakeRepository;
import app.postgresql.repositories.ModelRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
        //PopulateDB(true, 10, true);
        //listingRepo.saveAll(GenerateFakeListings(true, 1000));

        //Pageable firstPageWithTwoElements = PageRequest.of(0, 100);
        //Pageable sortByDate = PageRequest.of(0, 10, Sort.by("price").descending());
        //Page<Listing> allListings = listingRepository.findAll(sortByDate);

        System.out.println("Hol' up, preparing the database...");

        listingRepository.deleteAll();
        carRepository.deleteAll();
        modelRepository.deleteAll();
        makeRepository.deleteAll();
        makeRepository.saveAll(JsonReader.getMakesFromJson());
        modelRepository.saveAll(JsonReader.getModelsAndMakesFromJson(makeRepository));
        carRepository.saveAll(JsonReader.getCarsFromJson(makeRepository, modelRepository));
        listingRepository.saveAll(GenerateFakeListings(true, 100));

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
                l.setSeller_id(Generator.GenerateRandomAlphanumericString(24));
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
