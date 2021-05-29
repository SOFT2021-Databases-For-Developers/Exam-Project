package app.repositories;
import app.config.RibbonConfig;
import app.models.neo4j.Person;
import app.models.neo4j.PostListing;
import app.models.neo4j.Recommendation;
import app.models.neo4j.RecommendationPost;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@FeignClient("neo4j-service")
@RibbonClient(name = "neo4j-service", configuration = RibbonConfig.class)
public interface Neo4jService {

    @GetMapping("/{username}")
    List<Recommendation> getRecommendations(@PathVariable String username);

    @PostMapping()
    void addData(@RequestBody RecommendationPost post);
}
