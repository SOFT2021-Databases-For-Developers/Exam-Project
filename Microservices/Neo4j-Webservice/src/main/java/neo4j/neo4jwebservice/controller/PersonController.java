package neo4j.neo4jwebservice.controller;

import neo4j.neo4jwebservice.entities.Person;
import neo4j.neo4jwebservice.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
