package app.postgresql.repositories;

import app.postgresql.models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CarRepository extends CrudRepository<Car, Integer> {
    Collection<Car> findAll();
}
