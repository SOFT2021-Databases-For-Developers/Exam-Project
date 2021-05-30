package neo4j.neo4jwebservice.repositoryold;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4jSessionFactory {

    private final static Configuration configuration = new Configuration.Builder()
            .uri("bolt://localhost:7687")
            .credentials("neo4j", "admin")
            .build();
    private final static SessionFactory sessionFactory = new SessionFactory(configuration, "./neo4j/neo4jwebservice/entitiesold");
    private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

    public static Neo4jSessionFactory getInstance() {
        return factory;
    }

    // prevent external instantiation
    private Neo4jSessionFactory() {
    }

    public Session getNeo4jSession() {
        return sessionFactory.openSession();
    }
}