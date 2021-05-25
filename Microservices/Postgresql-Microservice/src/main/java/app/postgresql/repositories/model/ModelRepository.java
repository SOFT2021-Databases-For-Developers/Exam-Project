package app.postgresql.repositories.model;

import app.postgresql.models.Listing;
import app.postgresql.models.Model;
import app.postgresql.repositories.listing.ListingRepositoryCustom;
import app.postgresql.repositories.make.MakeRepositoryCustom;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, String>, ModelRepositoryCustom {
}
