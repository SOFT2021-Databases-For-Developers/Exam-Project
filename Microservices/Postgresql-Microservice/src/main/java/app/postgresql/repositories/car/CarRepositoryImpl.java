package app.postgresql.repositories.car;

import app.postgresql.models.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CarRepositoryImpl implements CarRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Car> findAll() {
        return em.createQuery("SELECT c from Car c").getResultList();
    }
}
