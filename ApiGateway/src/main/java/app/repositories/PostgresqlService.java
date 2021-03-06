package app.repositories;

import app.config.RibbonConfig;
import app.models.SimplePageImpl;
import app.models.postgresql.*;
import app.repositories.setup.FeignConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@FeignClient(
        value = "postgresql-service",
        configuration = { FeignConfig.class })
@RibbonClient(name = "postgresql-service", configuration = RibbonConfig.class)
public interface PostgresqlService {

    /* MAKES */
    @GetMapping("/makes")
    SimplePageImpl<Make> getMakes(Pageable pageable);
    @GetMapping("/makes/all")
    Collection<Make> getMakesUnPaginated();
    @GetMapping("/makes/{id}")
    Make getMakeById(@PathVariable int id);
    @PutMapping("/makes/{id}")
    Make updateMake(@PathVariable int id, @RequestBody Make make);
    @PostMapping("/makes")
    Make saveMake(@RequestBody Make make);
    @DeleteMapping("makes/{id}")
    Make deleteMakeById(@PathVariable int id);


    /* MODELS */
    @GetMapping("/models")
    SimplePageImpl<Model> getModels(Pageable pageable);
    @GetMapping("/models/all")
    Collection<Model> getModelsUnPaginated();
    @GetMapping("/models/{id}")
    Model getModelById(@PathVariable int id);
    @PutMapping("/models/{id}")
    Model updateModel(@PathVariable int id, @RequestBody Model model);
    @PostMapping("/models")
    Model saveModel(@RequestBody Model model);
    @DeleteMapping("models/{id}")
    Model deleteModelById(@PathVariable int id);

    /* CAR */
    @GetMapping("/cars")
    SimplePageImpl<Car> getCars(Pageable pageable);
    @GetMapping("/cars/all")
    Collection<Car> getCarsUnPaginated();
    @GetMapping("/cars/{id}")
    Car getCarById(@PathVariable int id);
    @PutMapping("/cars/{id}")
    Car updateCar(@PathVariable int id, @RequestBody Car car);
    @PostMapping("/cars")
    Car saveCar(@RequestBody Car car);
    @DeleteMapping("cars/{id}")
    Car deleteCarById(@PathVariable int id);

    /* LISTING */
    @GetMapping("/listings")
    SimplePageImpl<Listing> getListings(Pageable pageable);
    @GetMapping("/listings/all")
    Collection<Listing> getListingsUnPaginated();
    @GetMapping("/listings/{id}")
    Listing getListingsById(@PathVariable int id);
    @GetMapping("/listings/seller/{seller}")
    Collection<Listing> getListingsBySeller(@PathVariable String seller);
    @GetMapping("/listings/make/{name}")
    Collection<Listing> getListingsByMake(@PathVariable String name);
    @PutMapping("/listings/{id}")
    Listing updateListing(@PathVariable int id, @RequestBody Listing listing);
    @PutMapping("/listings/{id}/status/set/{status}")
    Listing updateListingStatus(@PathVariable Integer id, @PathVariable Status status);
    @PostMapping("/listings")
    Listing saveListing(@RequestBody Listing listing);
    @DeleteMapping("listings/{id}")
    Listing deleteListingById(@PathVariable int id);

}
