package neo4j.neo4jwebservice.entities;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Node
public class Person {

    @Id @GeneratedValue private Long id;

    private String name;

    private Person() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Relationship(type = "SEEN")
    public Set<Listing> seen;

    public void addSeenListing(Listing l)
    {
        if(seen == null)
        {
            seen = new HashSet<>();
        }
        seen.add(l);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}