package app.postgresql.repositories.car;

import app.postgresql.models.Car;

import java.util.List;

public interface CarRepositoryCustom {
    List<Car> findAll();
}
