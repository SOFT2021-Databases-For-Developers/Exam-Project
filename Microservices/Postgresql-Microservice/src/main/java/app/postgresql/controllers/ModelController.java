package app.postgresql.controllers;

import app.postgresql.models.Listing;
import app.postgresql.models.Model;
import app.postgresql.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("models")
public class ModelController {
    @Autowired
    private ModelRepository modelRepository;

    @RequestMapping(method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Model>> getModels() {
        Collection<Model> l = modelRepository.findAll();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> getPerson(@PathVariable String name) {
        try {
            Model l = modelRepository.findByName(name);

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
