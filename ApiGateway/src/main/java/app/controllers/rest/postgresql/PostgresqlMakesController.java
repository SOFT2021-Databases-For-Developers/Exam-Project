package app.controllers.rest.postgresql;

import app.models.postgresql.Make;
import app.repositories.PostgresqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/makes")
public class PostgresqlMakesController {
    private final PostgresqlService postgresqlService;

    public PostgresqlMakesController(PostgresqlService postgresqlService) {
        this.postgresqlService = postgresqlService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Make>> getMakesPaginated(Pageable pageable) {
        Page<Make> l = postgresqlService.getMakes(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Make>> getMakesUnpaginated() {
        Collection<Make> l = postgresqlService.getMakesUnPaginated();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> getMake(@PathVariable int id) {
        Make make = postgresqlService.getMakeById(id);
        if (make != null) {
            return new ResponseEntity<>(make, HttpStatus.OK);
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
            return new ResponseEntity<>(postgresqlService.saveMake(_make), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Make> updateMake(@PathVariable("id") int id, @RequestBody Make make) {
        Make  m = postgresqlService.getMakeById(id);
        if (m != null) {
            Make _make = m;
            _make.setName(make.getName());
            return new ResponseEntity<>(postgresqlService.saveMake(_make), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteMake(@PathVariable("id") int id) {
        try {
            postgresqlService.deleteMakeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
