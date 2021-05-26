package app.postgresql.controllers;

import app.postgresql.models.Make;
import app.postgresql.models.Model;
import app.postgresql.repositories.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("makes")
public class MakeController {
    @Autowired
    private MakeRepository makeRepository;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Make>> getModels() {
        Collection<Make> l = makeRepository.findAll();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> getPerson(@PathVariable String name) {
        try {
            Make l = makeRepository.findByName(name);

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
