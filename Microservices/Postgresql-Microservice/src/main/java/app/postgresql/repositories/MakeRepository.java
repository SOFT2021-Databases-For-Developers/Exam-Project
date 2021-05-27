package app.postgresql.repositories;

import app.postgresql.models.Make;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface MakeRepository extends PagingAndSortingRepository<Make, Integer> {
    Collection<Make> findAll();
    Make findByName(String name);
}