package app.postgresql.controllers;

import app.postgresql.helpers.Generator;
import app.postgresql.models.Listing;
import app.postgresql.models.Status;
import app.postgresql.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ListingsController {

    @Autowired
    private ListingRepository listingRepository;

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Listing>> getListingsaginated(Pageable pageable) {
        Page<Listing> l = listingRepository.findAll(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Listing>> getListingsUnpaginated() {
        Collection<Listing> l = listingRepository.findAll();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/make/{name}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Listing>> getListingsPaginatedByMake(@PathVariable String name) {
        Collection<Listing> l = listingRepository.findByCarMakeName(name);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Listing> getListing(@PathVariable int id) {
        Listing l = listingRepository.findOneById(id);
        if (l != null) {
            return new ResponseEntity<>(l, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }



    @RequestMapping(value = "/seller/{seller}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Listing>> getListingsForSeller(@PathVariable String seller) {
        Collection<Listing> l = listingRepository.findBySeller(seller);
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
            _listing.setSeller(listing.getSeller());
            _listing.setCar(listing.getCar());
            _listing.setPrice(listing.getPrice());
            _listing.setCreated_on(new Date());
            _listing.setKm(listing.getKm());
            _listing.setDescription(listing.getDescription());
            _listing.setTitle(Generator.GenerateListingTitle(_listing.getCar().getMake().getName(), _listing.getCar().getModel().getName(), _listing.getCar().getModel().getYear(), _listing.getKm()));
            _listing.setStatus(Status.ACTIVE);
            return new ResponseEntity<>(listingRepository.save(_listing), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Listing> updateListing(@PathVariable("id") int id, @RequestBody Listing listing) {
        Optional<Listing> listingOptional = listingRepository.findById(id);
        if (listingOptional.isPresent()) {
            Listing _listing = listingOptional.get();
            _listing.setPrice(listing.getPrice());
            _listing.setKm(listing.getKm());
            _listing.setDescription(listing.getDescription());
            _listing.setTitle(listing.getTitle());
            return new ResponseEntity<>(listingRepository.save(_listing), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/status/set/{status}")
    public ResponseEntity<Listing> updateListingStatus(@PathVariable int id, @PathVariable Status status) {
        Listing l = listingRepository.findOneById(id);
        if (l != null && ((status.equals(Status.ACTIVE) || (status.equals(Status.SOLD))))) {
            l.setStatus(status);
            return new ResponseEntity<>(listingRepository.save(l), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteListing(@PathVariable("id") int id) {
        try {
            listingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
