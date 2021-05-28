package neo4j.neo4jwebservice.repository;

import java.util.List;

import neo4j.neo4jwebservice.entities.Listing;
import neo4j.neo4jwebservice.entities.Make;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MakeRepository extends Neo4jRepository<Make, Long>{
}
