package app.controllers.rest.postgresql;

import app.models.mongo.User;
import app.models.postgresql.Listing;
import app.models.postgresql.Status;
import app.repositories.MongoService;
import app.repositories.PostgresqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("listings")
public class PostgresqlListingsController {
    private final PostgresqlService postgresqlService;
    private final MongoService mongoService;

    public PostgresqlListingsController(PostgresqlService postgresqlService, MongoService mongoService) {
        this.postgresqlService = postgresqlService;
        this.mongoService = mongoService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Listing>> getListingsPaginated(Pageable pageable) {
        Page<Listing> l = postgresqlService.getListings(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/make/{name}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Listing>> getListingsPaginatedByMake(@PathVariable String name) {
        Collection<Listing> l = postgresqlService.getListingsByMake(name);
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
        User _user = mongoService.getUserById(listing.getSeller());
        System.out.println(_user);
        try {
            Listing _listing = new Listing();
            _listing.setSeller(_user.getId());
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

    @PutMapping("/{id}/status/set/{status}")
    public ResponseEntity<Listing> updateListingStatus(@PathVariable int id, @PathVariable Status status) {
        Listing l = postgresqlService.getListingsById(id);
        if (l != null && ((status.equals(Status.ACTIVE) || (status.equals(Status.SOLD))))) {
            l.setStatus(status);
            return new ResponseEntity<>(postgresqlService.saveListing(l), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
