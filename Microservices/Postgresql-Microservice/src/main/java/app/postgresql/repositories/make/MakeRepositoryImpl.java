package app.postgresql.repositories.make;

import app.postgresql.models.Make;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MakeRepositoryImpl implements MakeRepositoryCustom {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Make> findAll() {
        return em.createQuery("SELECT m from Make m").getResultList();
    }

    @Override
    public Make findByName(String name) {
        return em.find(Make.class, name);
    }
}
