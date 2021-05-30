package neo4j.neo4jwebservice;

import neo4j.neo4jwebservice.entities.Listing;
import neo4j.neo4jwebservice.entities.Make;
import neo4j.neo4jwebservice.entities.Person;
import neo4j.neo4jwebservice.repository.ListingRepository;
import neo4j.neo4jwebservice.repository.MakeRepository;
import neo4j.neo4jwebservice.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


//@EnableNeo4jRepositories
@EnableDiscoveryClient
@SpringBootApplication
public class Neo4jwebserviceApplication {

    private final static Logger log = LoggerFactory.getLogger(Neo4jwebserviceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Neo4jwebserviceApplication.class, args);
    }

    /*
    @Bean
    CommandLineRunner demo(PersonRepository personRepository, ListingRepository listingRepository, MakeRepository makeRepository) {
        return args -> {

            personRepository.deleteAll();
            listingRepository.deleteAll();
            makeRepository.deleteAll();

            Person jonatan = new Person("Jonatan");
            personRepository.save(jonatan);
            jonatan = personRepository.findByName(jonatan.getName());
            Make m = new Make("BMW");
            jonatan.addLike(m);
            personRepository.save(jonatan);
            jonatan = personRepository.findByName(jonatan.getName());
            Listing l = new Listing(333);
            for(Make make : jonatan.likes)
            {
                make.addSeenListing(l);
            }
            personRepository.save(jonatan);


            jonatan = personRepository.findByName(jonatan.getName());
            m = new Make("AUDI");
            jonatan.addLike(m);
            personRepository.save(jonatan);
            jonatan = personRepository.findByName(jonatan.getName());
            for(Make make : jonatan.likes)
            {
                make.addSeenListing(new Listing(1));
                make.addSeenListing(new Listing(2));
            }
            personRepository.save(jonatan);


        };
    }

     */
}

