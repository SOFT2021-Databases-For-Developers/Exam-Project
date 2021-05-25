package app.postgresql.repositories.model;

import app.postgresql.models.Car;
import app.postgresql.models.Listing;
import app.postgresql.models.Model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ModelRepositoryImpl implements ModelRepositoryCustom {
    @PersistenceContext
    EntityManager em;


    @Override
    public List<Model> findAll() {
        return em.createQuery("SELECT m from Model m").getResultList();
    }
}
