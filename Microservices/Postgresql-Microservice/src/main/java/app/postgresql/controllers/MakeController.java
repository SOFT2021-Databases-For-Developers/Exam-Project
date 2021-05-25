package app.postgresql.controllers;

import app.postgresql.models.Listing;
import app.postgresql.models.Make;
import app.postgresql.repositories.listing.ListingRepository;
import app.postgresql.repositories.make.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/makes")
public class MakeController {

    @Autowired
    MakeRepository repo;

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public List<Make> findAllL() {
        return repo.findAll();
    }

    @GetMapping("/{name}")
    @CrossOrigin(origins = "*")
    public Make findByName(@PathVariable String name) {
        return repo.findByName(name);
    }

    @PostMapping("")
    @CrossOrigin(origins = "*")
    public Make createMake(@RequestBody Make make) {
        try {
            return repo.save(make);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @PostMapping("/new/{make}")
    @CrossOrigin(origins = "*")
    public Make createMake(@PathVariable String make) {
        try {
            Make m = new Make(make);
            return repo.save(m);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
