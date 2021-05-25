package app.postgresql.controllers;

import app.postgresql.models.Make;
import app.postgresql.models.Model;
import app.postgresql.repositories.model.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    @Autowired
    ModelRepository repo;

    @GetMapping("")
    public List<Model> findAll() {
        return repo.findAll();
    }

    @PostMapping("/new/{make}/{model}")
    public Model createMake(@PathVariable String make, @PathVariable String model) {
        try {
            Model m = new Model(model, make);
            return repo.save(m);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
