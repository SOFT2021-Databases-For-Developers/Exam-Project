package app.postgresql.repositories.listing;

import app.postgresql.models.Listing;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class ListingRepositoryImpl implements ListingRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Listing> findAll() {
        return em.createQuery("SELECT l from Listing l").getResultList();
    }

//    @Override
//    public List<Listing> findByMakeAndModel(String make, String model) {
//        StoredProcedureQuery query = em
//                .createStoredProcedureQuery("listing.findAllByMakeAndModel")
//                .setParameter("_make", make)
//                .setParameter("_model", model);
//        query.execute();
//        return query.getResultList();
//    }

    @Override
    public List<Listing> findAllByMakeAndModel(String make_name, String model_name) {
        return em.createQuery("SELECT l FROM Listing l WHERE l.make_name = :make_name AND l.model_name = :model_name")
                .setParameter("make_name", make_name)
                .setParameter("model_name", model_name)
                .getResultList();
    }

    @Override
    public Listing findById(int id) {
        Listing movie = em.find(Listing.class, id);
        return movie;
    }
}
