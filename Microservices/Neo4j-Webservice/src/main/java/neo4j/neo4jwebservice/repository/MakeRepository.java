package neo4j.neo4jwebservice.repository;

import neo4j.neo4jwebservice.entities.Make;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface MakeRepository extends Neo4jRepository<Make, Long>{
    @Query("MATCH (p:Person {name:$username})-[:LIKES]->(make:Make {make:$make}) RETURN make;")
    Make findMakeByUser(@Param("username") String username, @Param("make") String make);
}
