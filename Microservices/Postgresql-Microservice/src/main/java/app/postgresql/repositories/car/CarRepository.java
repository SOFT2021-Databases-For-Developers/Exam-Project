package app.postgresql.repositories.car;

import app.postgresql.models.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer>, CarRepositoryCustom {
}
