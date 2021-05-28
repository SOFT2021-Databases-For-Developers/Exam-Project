package app.postgresql.controllers;

import app.postgresql.models.Car;
import app.postgresql.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarsController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Page<Car>> getCarsPaginated(Pageable pageable) {
        Page<Car> l = carRepository.findAll(pageable);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Collection<Car>> getCarsUnPaginated() {
        Collection<Car> l = carRepository.findAll();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @CrossOrigin(origins = "*")
    public ResponseEntity<Car> getCarById(@PathVariable int id) {
        Optional<Car> l = carRepository.findById(id);
        if (l.isPresent()) {
            return new ResponseEntity<>(l.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        try {
            return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Car> updateCar(@PathVariable("id") int id, @RequestBody Car car) {
        Optional<Car> carOptional = carRepository.findById(id);
        if (carOptional.isPresent()) {
            Car _car = new Car();
            _car.setId(carOptional.get().getId());
            return new ResponseEntity<>(carRepository.save(_car), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") int id) {
        try {
            carRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
