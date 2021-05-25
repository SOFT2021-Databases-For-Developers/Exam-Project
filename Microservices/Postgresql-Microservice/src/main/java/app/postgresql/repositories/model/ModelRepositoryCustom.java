package app.postgresql.repositories.model;

import app.postgresql.models.Model;

import java.util.List;

public interface ModelRepositoryCustom {
    List<Model> findAll();
}
