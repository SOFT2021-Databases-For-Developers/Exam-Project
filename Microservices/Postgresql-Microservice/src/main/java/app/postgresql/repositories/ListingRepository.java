package app.postgresql.repositories;

import app.postgresql.models.Listing;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface ListingRepository extends PagingAndSortingRepository<Listing, Integer> {
    Collection<Listing> findAll();
    Listing findOneByid(int id);
}
