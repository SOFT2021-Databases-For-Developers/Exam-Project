package app.postgresql.repositories;

import app.postgresql.models.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ListingRepository extends PagingAndSortingRepository<Listing, Integer> {
    Collection<Listing> findAll();
    Collection<Listing> findBySeller(String seller);
    Listing findOneById(int id);
    Collection<Listing> findByCarMakeName(String name);
}
