package app.postgresql.repositories;

import app.postgresql.models.Listing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class ListingRepositoryImpl implements ListingRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Listing> findAll() {
        StoredProcedureQuery findByYearProcedure =
                em.createNamedStoredProcedureQuery("findAllListings");
        return findByYearProcedure.getResultList();
    }

    @Override
    public Listing findAllById(int id) {
        Listing movie = em.find(Listing.class, id);
        return movie;
    }
}
