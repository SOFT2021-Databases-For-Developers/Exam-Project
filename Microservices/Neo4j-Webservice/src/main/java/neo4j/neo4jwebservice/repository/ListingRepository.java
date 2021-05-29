package neo4j.neo4jwebservice.repository;

import java.util.List;

import neo4j.neo4jwebservice.entities.Listing;
import neo4j.neo4jwebservice.entities.Make;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface ListingRepository extends Neo4jRepository<Listing, Long>{
    @Query("MATCH (p:Person {name:$username})-[:LIKES]->(make:Make {make:$make})-[:LISTINGS]->(listing:Listing {listingId:$listingId}) RETURN listing;")
    Listing findListingByMakeAndPerson(@Param("username") String username, @Param("make") String make, @Param("listingId") long listingId);
}
