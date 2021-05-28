package app.postgresql.repositories;

import app.postgresql.models.Model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ModelRepository extends PagingAndSortingRepository<Model, Integer> {
    Collection<Model> findAll();
    Model findOneById(int id);
    Model findByName(String name);
}
