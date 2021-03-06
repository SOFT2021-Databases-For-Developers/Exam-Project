package neo4j.neo4jwebservice.controller;

import neo4j.neo4jwebservice.Querys.Neo4jQuerys;
import neo4j.neo4jwebservice.dto.PostListing;
import neo4j.neo4jwebservice.dto.Recommendation;
import neo4j.neo4jwebservice.dto.RecommendationPost;
import neo4j.neo4jwebservice.entities.Listing;
import neo4j.neo4jwebservice.entities.Make;
import neo4j.neo4jwebservice.entities.Person;
import neo4j.neo4jwebservice.entitiesold.PersonOld;
import neo4j.neo4jwebservice.repository.ListingRepository;
import neo4j.neo4jwebservice.repository.MakeRepository;
import neo4j.neo4jwebservice.repository.PersonRepository;
import neo4j.neo4jwebservice.repositoryold.GenericImpl;
import neo4j.neo4jwebservice.repositoryold.PersonOldImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    /*
    private PersonRepository personRepository;
    private MakeRepository makeRepository;
    private ListingRepository listingRepository;
    private PersonOldImpl oldGeneric;

     */
    private Neo4jQuerys neo4jQuerys;

    /*
    public PersonController(PersonRepository personRepository, MakeRepository makeRepository, ListingRepository listingRepository) {
        this.personRepository = personRepository;
        this.makeRepository = makeRepository;
        this.listingRepository = listingRepository;
        oldGeneric = new PersonOldImpl();
        neo4jQuerys = new Neo4jQuerys();
    }
     */

    public PersonController() {
        this.neo4jQuerys = new Neo4jQuerys();
    }

    @GetMapping("/{username}")
    public List<Recommendation> getRecommendations(@PathVariable String username)
    {
        return neo4jQuerys.getRecommendationsForPerson(username);
    }

    @PostMapping()
    public void addData(@RequestBody RecommendationPost post)
    {
        neo4jQuerys.createRecommendation(post);
    }

    /*

    @GetMapping("/{username}")
    public Person get(@PathVariable String username)
    {
        return personRepository.findByName(username);
    }
    @PostMapping
    public Person save(@RequestBody Person p)
    {
        if(p.getId() != null)
        {
            personRepository.save(p);
            return personRepository.findByName(p.getName());
        }
        else if(personRepository.findByName(p.getName()) != null)
        {
            return null;
        }
        else
        {
            personRepository.save(p);
            return personRepository.findByName(p.getName());
        }
    }

    @GetMapping("/{username}/recommendation")
    public List<Recommendation> rec(@PathVariable String username)
    {
        Person p = personRepository.findByName(username);
        List<Recommendation> recommendations = new ArrayList<>();
        for(Make m : p.likes)
        {
            recommendations.add(new Recommendation(m.getMake(), m.listings.size()));
        }
        return recommendations;
    }

    @PostMapping("/{username}/recommendation")
    public void addRec(@PathVariable String username, @RequestBody PostListing pl)
    {
        try{
            var p = personRepository.findByName(username);
            var m = makeRepository.findMakeByUser(username, pl.getMake());
            var l = listingRepository.findListingByMakeAndPerson(username, pl.getMake(), pl.getListingId());
            if(p == null)
            {
                personRepository.save(new Person(username));
                p = personRepository.findByName(username);
                createAndSaveMakeAndListing(username, pl, p);
            }
            else if(m == null)
            {
                createAndSaveMakeAndListing(username, pl, p);
            }
            else if(l == null)
            {
                createAndSaveListing(pl, m);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void createAndSaveMakeAndListing(String username, PostListing pl, Person p) {
        Make m;
        p.addLike(new Make(pl.getMake()));
        personRepository.save(p);
        m = makeRepository.findMakeByUser(username, pl.getMake());
        createAndSaveListing(pl, m);
    }

    private void createAndSaveListing(@RequestBody PostListing pl, Make m) {
        m.addSeenListing(new Listing(pl.getListingId()));
        makeRepository.save(m);
    }

     */

}
