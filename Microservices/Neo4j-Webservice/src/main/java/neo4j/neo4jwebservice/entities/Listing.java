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
public class Listing {

    @Id @GeneratedValue private Long id;

    private long listingId;

    private String make;

    public Listing() {
    }

    public long getListingId() {
        return listingId;
    }

    public String getMake() {
        return make;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
