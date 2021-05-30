package neo4j.neo4jwebservice.repositoryold;

import neo4j.neo4jwebservice.entitiesold.PersonOld;

public class PersonOldImpl extends GenericImpl<PersonOld>{
    @Override
    Class<PersonOld> getEntityType() {
        return PersonOld.class;
    }
}
