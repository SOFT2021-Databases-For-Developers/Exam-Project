package neo4j.neo4jwebservice.repository;

import java.util.List;

import neo4j.neo4jwebservice.entities.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
    //List<Person> findByTeammatesName(String name);
}