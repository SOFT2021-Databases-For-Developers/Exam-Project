package neo4j.neo4jwebservice.controller;

import neo4j.neo4jwebservice.dto.Recommendation;
import neo4j.neo4jwebservice.entities.Listing;
import neo4j.neo4jwebservice.entities.Make;
import neo4j.neo4jwebservice.entities.Person;
import neo4j.neo4jwebservice.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    private PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/test")
    public Person test()
    {
        return new Person("Jonatan");
    }

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

}
