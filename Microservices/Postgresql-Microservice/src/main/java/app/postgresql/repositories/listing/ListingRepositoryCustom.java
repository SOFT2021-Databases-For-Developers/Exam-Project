package app.postgresql.repositories.listing;

import app.postgresql.models.Listing;

import java.util.List;

public interface ListingRepositoryCustom {
    List<Listing> findAll();
    List<Listing> findAllByMakeAndModel(String make_name, String model_name);
    Listing findById(int id);
}
