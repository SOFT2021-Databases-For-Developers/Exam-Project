package app.postgresql.repositories;

import app.postgresql.models.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface CarRepository extends PagingAndSortingRepository<Car, Integer> {
    Collection<Car> findAll();
}
