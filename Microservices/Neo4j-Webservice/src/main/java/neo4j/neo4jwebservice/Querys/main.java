package neo4j.neo4jwebservice.Querys;

public class main {
    public static void main(String[] args) {
        Neo4jQuerys ne = new Neo4jQuerys();
        ne.getPersonByName("Jonas");
        ne.close();
    }
}
