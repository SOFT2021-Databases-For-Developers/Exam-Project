package app.models.neo4j;

public class Recommendation {
    String make;
    int count;

    public Recommendation(String make, int count) {
        this.make = make;
        this.count = count;
    }

    public String getMake() {
        return make;
    }

    public int getCount() {
        return count;
    }
}
