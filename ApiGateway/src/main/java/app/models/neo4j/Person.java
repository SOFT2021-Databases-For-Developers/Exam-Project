package app.models.neo4j;

import java.util.HashSet;
import java.util.Set;

public class Person {
    private Long id;

    private String name;

    private Person() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Set<Make> likes;

    public void addLike(Make m)
    {
        if(likes == null)
        {
            likes = new HashSet<>();
        }
        likes.add(m);
    }
}
