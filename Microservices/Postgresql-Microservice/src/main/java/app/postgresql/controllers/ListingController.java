package app.postgresql.controllers;

import app.postgresql.models.Listing;
import app.postgresql.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class ListingController {

    @Autowired
    ListingRepository repo;


    @GetMapping("")
    public List<Listing> findAllListings() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Listing findListingById(@PathVariable int id) {
        return repo.findAllById(id);
    }
}
