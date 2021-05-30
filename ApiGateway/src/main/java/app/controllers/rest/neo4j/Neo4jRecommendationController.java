package app.controllers.rest.neo4j;

import app.models.neo4j.Person;
import app.models.neo4j.PostListing;
import app.models.neo4j.Recommendation;
import app.models.neo4j.RecommendationPost;
import app.repositories.Neo4jService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class Neo4jRecommendationController {
    private final Neo4jService neo4jService;

    public Neo4jRecommendationController(Neo4jService neo4jService) {
        this.neo4jService = neo4jService;
    }

    @GetMapping("/{username}")
    public List<Recommendation> getRecommendations(@PathVariable String username)
    {
        return neo4jService.getRecommendations(username);
    }

    @PostMapping()
    public void addData(@RequestBody RecommendationPost post)
    {
        neo4jService.addData(post);
    }
}
