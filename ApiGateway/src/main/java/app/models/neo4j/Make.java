package app.models.neo4j;

import java.util.HashSet;
import java.util.Set;

public class Make {
    private Long id;

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
