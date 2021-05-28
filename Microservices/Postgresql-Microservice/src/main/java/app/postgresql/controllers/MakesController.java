package app.postgresql.controllers;

import app.postgresql.models.Listing;
import app.postgresql.models.Make;
import app.postgresql.models.Model;
import app.postgresql.repositories.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("makes")
public class MakesController {
    @Autowired
    private MakeRepository makeRepository;

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Make>> getMakesPaginated(Pageable pageable) {
        Page<Make> l = makeRepository.findAll(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Make>> getMakesUnpaginated() {
        Collection<Make> l = makeRepository.findAll();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> getMake(@PathVariable int id) {
        Optional<Make> make = makeRepository.findById(id);
        if (make.isPresent()) {
            return new ResponseEntity<>(make.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> createMake(@RequestBody Make make) {
        try {
            Make _make = new Make();
            _make.setName(make.getName());
            return new ResponseEntity<>(makeRepository.save(_make), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> updateMake(@PathVariable("id") int id, @RequestBody Make make) {
        Optional<Make> makeOptional = makeRepository.findById(id);
        if (makeOptional.isPresent()) {
            Make _make = makeOptional.get();
            _make.setName(make.getName());
            return new ResponseEntity<>(makeRepository.save(_make), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteMake(@PathVariable("id") int id) {
        try {
            makeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
