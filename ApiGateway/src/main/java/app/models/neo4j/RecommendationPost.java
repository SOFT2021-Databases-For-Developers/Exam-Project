package app.models.neo4j;

public class RecommendationPost {
    private String name;
    private String make;
    private long listingId;

    public RecommendationPost(String name, String make, long listingId) {
        this.name = name;
        this.make = make;
        this.listingId = listingId;
    }

    public RecommendationPost() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public long getListingId() {
        return listingId;
    }

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }
}
