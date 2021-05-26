package app.postgresql.repositories;

import app.postgresql.models.Listing;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ListingRepository extends CrudRepository<Listing, Integer> {
    Collection<Listing> findAll();
    Listing findOneByid(int id);
}
