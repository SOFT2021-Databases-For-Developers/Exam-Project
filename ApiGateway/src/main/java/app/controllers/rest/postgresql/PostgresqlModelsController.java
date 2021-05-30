package app.controllers.rest.postgresql;

import app.models.postgresql.Model;
import app.repositories.PostgresqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/models")
public class PostgresqlModelsController {
    private final PostgresqlService postgresqlService;

    public PostgresqlModelsController(PostgresqlService postgresqlService) {
        this.postgresqlService = postgresqlService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Model>> getModels(Pageable pageable) {
        Page<Model> l = postgresqlService.getModels(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Model>> getModelsUnpaginated() {
        Collection<Model> l = postgresqlService.getModelsUnPaginated();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> getModelById(@PathVariable int id) {
        Model model = postgresqlService.getModelById(id);
        if (model != null) {
            return new ResponseEntity<>(model, HttpStatus.OK);
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
            return new ResponseEntity<>(postgresqlService.saveModel(_model), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Model> updateModel(@PathVariable("id") int id, @RequestBody Model model) {
        Model modelFetched = postgresqlService.getModelById(id);
        if (modelFetched != null) {
            Model _model = modelFetched;
            _model.setName(model.getName());
            _model.setYear(model.getYear());
            _model.setMake(model.getMake());
            return new ResponseEntity<>(postgresqlService.updateModel(id, _model), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable("id") int id) {
        try {
            postgresqlService.deleteModelById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
