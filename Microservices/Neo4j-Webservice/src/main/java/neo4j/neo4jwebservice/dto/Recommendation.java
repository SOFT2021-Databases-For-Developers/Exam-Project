package neo4j.neo4jwebservice.dto;

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

    @Override
    public String toString() {
        return "Recommendation{" +
                "make='" + make + '\'' +
                ", count=" + count +
                '}';
    }
}
