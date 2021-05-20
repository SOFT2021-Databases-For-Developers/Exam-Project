package app.postgresql.controllers;

import app.postgresql.models.Listing;
import app.postgresql.repositories.listing.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;;
import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    ListingRepository repo;


    @GetMapping("")
    public List<Listing> findAllL() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Listing findById(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping("/{make}/{model}")
    public List<Listing> findAllByMakeAndModel(@PathVariable String make, @PathVariable String model) {
        return null;
    }
}
