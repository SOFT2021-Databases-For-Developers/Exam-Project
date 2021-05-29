package neo4j.neo4jwebservice.Querys;

import neo4j.neo4jwebservice.dto.Recommendation;
import neo4j.neo4jwebservice.dto.RecommendationPost;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class Neo4jQuerys {
    Driver driver;

    public Neo4jQuerys() {
        this.driver = GraphDatabase.driver("bolt://localhost:7687",
                AuthTokens.basic("neo4j","admin"));
    }

    public Record getPersonByName(String name)
    {
        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "match(n:Person {name:$name}) return n;";

            var result = session.readTransaction(
                    tx -> tx.run(cypherQuery, parameters("name", name)));
            if(result.hasNext())
            {
                return result.single();
            }
            else
            {
                return null;
            }
        }
    }

    public void createPerson(String name)
    {
        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "CREATE (n:Person {name:$name, title: 'Developer'});";
            session.writeTransaction(tx -> tx.run(cypherQuery, parameters("name", name)));
        }
    }

    public void createMake(String name, String make)
    {
        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "match(n:Person {name:$name}) create(n)-[:LIKES]->(m:Make {make:$make});";
            session.writeTransaction(tx -> tx.run(cypherQuery, parameters("name", name,"make", make)));
        }
    }

    private Record getMake(String name, String make)
    {
        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "match(p:Person {name:$name})-[]->(m:Make{make:$make}) return m;";
            var r = session.readTransaction(tx -> tx.run(cypherQuery, parameters("name", name,"make", make)));
            if(r.hasNext())
            {
                return r.single();
            }
            return null;
        }
    }

    public void createListing(String name, String make, long listingId)
    {
        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "MATCH (p:Person {name:$name})-[:LIKES]->(make:Make {make:$make}) CREATE(make)-[:SEEN]->(l:Listing {listingId:$listingId})";
            session.writeTransaction(tx -> tx.run(cypherQuery, parameters("name", name,"make", make, "listingId", listingId)));
        }
    }

    public void getEntirePathForPerson(String name)
    {
        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "match(p:Person {name:$name})-[]->(m)-[]->(l) return p, m, l;";
            var result = session.readTransaction(tx -> tx.run(cypherQuery, parameters("name", name))).list();
            for(var i : result)
            {
                System.out.println(i);
            }
        }
    }

    public List<Recommendation> getRecommendationsForPerson(String name)
    {
        List<Recommendation> recommendations = new ArrayList<>();
        //match(n:Person{name:'Jonas'})-[]->(m:Make)-[]->(l:Listing) return {label: m.make, count: count(l)}
        try (Session session = driver.session(SessionConfig.forDatabase("neo4j"))) {

            String cypherQuery =
                    "match(n:Person{name:$name})-[]->(m:Make)-[]->(l:Listing) return {label: m.make, count: count(l)}";
            var result = session.readTransaction(tx -> tx.run(cypherQuery, parameters("name", name)));
            if(result.hasNext())
            {
                var list = result.list();
                for(var i : list)
                {
                    recommendations.add(new Recommendation(i.get(0).get("label").asString(), i.get(0).get("count").asInt()));
                }
            }

        }
        return recommendations;
    }

    public void createRecommendation(RecommendationPost rp)
    {
        try
        {
            var r = getPersonByName(rp.getName());
            if(r == null)
            {
                createPerson(rp.getName());
            }
            r = getMake(rp.getName(), rp.getMake());
            if (r == null)
            {
                createMake(rp.getName(), rp.getMake());
            }
            createListing(rp.getName(), rp.getMake(), rp.getListingId());
        }
        catch (Exception e)
        {

        }

    }

    public void close()
    {
        driver.close();
    }

}
