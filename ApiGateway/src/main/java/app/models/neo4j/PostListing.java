package app.models.neo4j;

public class PostListing {
    private String make;
    private Long listingId;

    public PostListing(String make, Long listingId) {
        this.make = make;
        this.listingId = listingId;
    }

    public PostListing() {
    }

    public String getMake() {
        return make;
    }

    public Long getListingId() {
        return listingId;
    }
}
