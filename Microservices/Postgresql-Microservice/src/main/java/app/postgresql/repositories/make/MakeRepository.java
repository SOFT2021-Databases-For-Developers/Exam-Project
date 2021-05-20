package app.postgresql.repositories.make;

import app.postgresql.models.Make;
import org.springframework.data.repository.CrudRepository;

public interface MakeRepository extends CrudRepository<Make, String>, MakeRepositoryCustom {
}
