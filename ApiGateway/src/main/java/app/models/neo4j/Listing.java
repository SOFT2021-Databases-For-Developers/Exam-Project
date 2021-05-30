package app.models.neo4j;

public class Listing {
    private Long id;

    private long listingId;

    public Listing() {
    }

    public long getListingId() {
        return listingId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Listing(long listingId) {
        this.listingId = listingId;
    }
}
