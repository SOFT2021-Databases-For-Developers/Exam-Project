package app.postgresql.repositories;

import app.postgresql.models.Listing;

import java.util.List;

public interface ListingRepositoryCustom {
    List<Listing> findAll();
    Listing findAllById(int id);
}
