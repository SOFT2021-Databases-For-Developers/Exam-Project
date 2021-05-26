package app.postgresql.repositories;

import app.postgresql.models.Make;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MakeRepository extends CrudRepository<Make, Integer> {
    Collection<Make> findAll();
    Make findByName(String name);
}
