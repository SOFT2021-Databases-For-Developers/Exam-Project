package app.postgresql.repositories;

import app.postgresql.models.Model;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ModelRepository extends CrudRepository<Model, Integer> {
    Collection<Model> findAll();
    Model findOneById(int id);
    Model findByName(String name);
}
