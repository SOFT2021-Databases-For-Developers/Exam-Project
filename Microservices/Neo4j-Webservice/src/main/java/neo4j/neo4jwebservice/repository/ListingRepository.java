package neo4j.neo4jwebservice.repository;

import java.util.List;

import neo4j.neo4jwebservice.entities.Listing;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ListingRepository extends Neo4jRepository<Listing, Long>{
}
