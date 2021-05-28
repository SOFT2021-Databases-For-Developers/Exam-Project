package app.postgresql.controllers;

import app.postgresql.models.Listing;
import app.postgresql.models.Make;
import app.postgresql.models.Model;
import app.postgresql.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("models")
public class ModelsController {
    @Autowired
    private ModelRepository modelRepository;

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Model>> getModelsPaginated(Pageable pageable) {
        Page<Model> l = modelRepository.findAll(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Model>> getModelsUnpaginated() {
        Collection<Model> l = modelRepository.findAll();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> getModel(@PathVariable int id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            return new ResponseEntity<>(model.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        try {
            Model _model = new Model();
            _model.setName(model.getName());
            _model.setMake(model.getMake());
            _model.setYear(model.getYear());
            return new ResponseEntity<>(modelRepository.save(_model), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> updateMake(@PathVariable("id") int id, @RequestBody Model model) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            Model _model = modelOptional.get();
            _model.setName(model.getName());
            _model.setYear(model.getYear());
            _model.setMake(model.getMake());
            return new ResponseEntity<>(modelRepository.save(_model), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteMake(@PathVariable("id") int id) {
        try {
            modelRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
