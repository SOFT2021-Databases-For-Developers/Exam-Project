package app.postgresql.controllers;

import app.postgresql.models.Listing;
import app.postgresql.repositories.listing.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    ListingRepository repo;


    @GetMapping("")
    @CrossOrigin(origins = "*")
    public List<Listing> findAllL() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Optional<Listing> findById(@PathVariable Integer id) {
        return repo.findById(id);
    }

    @GetMapping("/{make}/{model}")
    @CrossOrigin(origins = "*")
    public List<Listing> findAllByMakeAndModel(@PathVariable String make, @PathVariable String model) {
        return null;
    }
}
