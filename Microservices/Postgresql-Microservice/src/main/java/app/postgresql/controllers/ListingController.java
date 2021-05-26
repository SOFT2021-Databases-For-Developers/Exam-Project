package app.postgresql.controllers;

import app.postgresql.models.Listing;
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
import java.util.List;

@RestController
@RequestMapping("listings")
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Listing>> getListings() {
        Collection<Listing> l = listingRepository.findAll();
        for (Listing x : l) {
            System.out.println(x);
        }
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping(params = { "page", "size" })
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Listing>> getListingsPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Listing> l = listingRepository.findAll(pageable);
        for (Listing x : l) {
            System.out.println(x);
        }
        return new ResponseEntity<>(l, HttpStatus.OK);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Listing> getPerson(@PathVariable int id) {
        try {
            Listing l = listingRepository.findOneByid(id);

            if (l != null) {
                return new ResponseEntity<>(l, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
}
