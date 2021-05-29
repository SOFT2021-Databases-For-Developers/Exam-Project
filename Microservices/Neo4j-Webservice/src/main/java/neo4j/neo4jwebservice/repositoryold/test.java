package neo4j.neo4jwebservice.repositoryold;

public interface test<T> {
    Iterable<T> findAll();

    T find(Long id);

    void delete(Long id);

    void createOrUpdate(T object);
}
