package neo4j.neo4jwebservice.controller;

import neo4j.neo4jwebservice.entities.Person;
import neo4j.neo4jwebservice.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

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

}
