package app.controllers.rest.postgresql;

import app.models.postgresql.Car;
import app.models.postgresql.Make;
import app.repositories.PostgresqlService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class PostgresqlCarsController {
    private final PostgresqlService postgresqlService;

    public PostgresqlCarsController(PostgresqlService postgresqlService) {
        this.postgresqlService = postgresqlService;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Car>> getCarsPaginated(Pageable pageable) {
        Page<Car> l = postgresqlService.getCars(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Car>> getCarsUnPaginated() {
        Collection<Car> l = postgresqlService.getCarsUnPaginated();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        Car l = postgresqlService.getCarById(id);
        if (l != null) {
            return new ResponseEntity<>(l, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            return new ResponseEntity<>(postgresqlService.saveCar(car), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Car> updateCar(@PathVariable("id") int id, @RequestBody Car car) {
        Car carFetched = postgresqlService.getCarById(id);
        if (carFetched != null) {
            Car _car = new Car();
            _car.setId(carFetched.getId());
            return new ResponseEntity<>(postgresqlService.saveCar(_car), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") int id) {
        try {
            postgresqlService.deleteCarById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
