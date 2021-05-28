package app.postgresql.controllers;

import app.postgresql.helpers.Generator;
import app.postgresql.models.Listing;
import app.postgresql.models.Make;
import app.postgresql.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Listing> getListing(@PathVariable int id) {
        Listing l = listingRepository.findOneByid(id);
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
