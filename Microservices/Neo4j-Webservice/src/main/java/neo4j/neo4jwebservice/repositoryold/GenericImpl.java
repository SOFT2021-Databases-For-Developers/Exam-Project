package neo4j.neo4jwebservice.repositoryold;


import org.neo4j.ogm.session.Session;

public abstract class GenericImpl<T> implements test<T>{
    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
    protected Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

    @Override
    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(), DEPTH_LIST);
    }

    @Override
    public T find(Long id) {
        return session.load(getEntityType(), id, DEPTH_ENTITY);
    }

    @Override
    public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }

    @Override
    public void createOrUpdate(T entity) {
        session.save(entity, DEPTH_ENTITY);
    }

    abstract Class<T> getEntityType();
}
