package app.postgresql.repositories.make;

import app.postgresql.models.Make;

import java.util.List;

public interface MakeRepositoryCustom {
    List<Make> findAll();
    Make findByName(String name);
}
