package neo4j.neo4jwebservice.repository;

import java.util.Collection;
import java.util.List;

import neo4j.neo4jwebservice.entities.Make;
import neo4j.neo4jwebservice.entities.Person;
import org.neo4j.cypherdsl.core.Node;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Person findByName(String name);
}