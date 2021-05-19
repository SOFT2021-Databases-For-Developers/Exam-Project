package app.postgresql.repositories;

import app.postgresql.models.Listing;
import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, Integer>, ListingRepositoryCustom {
}
