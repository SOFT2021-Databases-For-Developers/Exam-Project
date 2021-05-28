package app.controllers.rest;

import app.models.postgresql.Listing;
import app.repositories.PostgresqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("listings")
public class PostgresqlListingsController {
    private PostgresqlService postgresqlService;

    public PostgresqlListingsController(PostgresqlService postgresqlService) {
        this.postgresqlService = postgresqlService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Listing>> getListingsPaginated(Pageable pageable) {
        Page<Listing> l = postgresqlService.getListings(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Listing>> getListingsUnPaginated() {
        Collection<Listing> l = postgresqlService.getListingsUnPaginated();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Listing> getListing(@PathVariable int id) {
        Listing l = postgresqlService.getListingsById(id);
        if (l != null) {
            return new ResponseEntity<>(l, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
        try {
            Listing _listing = new Listing();
            _listing.setSeller_id(listing.getSeller_id());
            _listing.setCar(listing.getCar());
            _listing.setPrice(listing.getPrice());
            _listing.setCreated_on(new Date());
            _listing.setKm(listing.getKm());
            _listing.setDescription(listing.getDescription());
            _listing.setTitle(listing.getTitle());
            return new ResponseEntity<>(postgresqlService.saveListing(_listing), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Listing> updateListing(@PathVariable("id") int id, @RequestBody Listing listing) {
        Listing fetchedListing = postgresqlService.getListingsById(id);
        if (fetchedListing != null) {
            Listing _listing = fetchedListing;
            _listing.setPrice(listing.getPrice());
            _listing.setKm(listing.getKm());
            _listing.setDescription(listing.getDescription());
            _listing.setTitle(listing.getTitle());
            return new ResponseEntity<>(postgresqlService.saveListing(_listing), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteListing(@PathVariable("id") int id) {
        try {
            postgresqlService.deleteListingById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
