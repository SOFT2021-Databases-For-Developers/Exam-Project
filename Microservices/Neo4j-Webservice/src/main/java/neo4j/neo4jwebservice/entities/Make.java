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
public class Make {
    @Id @GeneratedValue private Long id;

    private String make;

    public Make() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Relationship(type = "LISTINGS")
    public Set<Listing> listings;

    public void addSeenListing(Listing l)
    {
        if(listings == null)
        {
            listings = new HashSet<>();
        }
        listings.add(l);
    }

    public Make(String make) {
        this.make = make;
    }
}
