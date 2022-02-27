# Spring Data MongoDB - Relation Modelling
###### spring.io : https://spring.io/blog/2021/11/29/spring-data-mongodb-relation-modelling
###### stackoverflow : https://stackoverflow.com/questions/62371473/mongodb-spring-boot-definition-of-a-onetomany-manytoone-relationship

I'd advise your read this excellent MongoDb documentation article that gives recommendations about how you should model your relationships: https://docs.mongodb.com/manual/reference/database-references

There are 3 options:

###### 1- Embedding the related entity
###### 2- Using manual references
###### 3- Using dbref

### Embedding:

For many use cases in MongoDB, the denormalized data model where related data is stored within a single document will be optimal

### Manual references vs DBRef

Unless you have a compelling reason to use DBRefs, use manual references instead.

If you can use an embedded document, then just embed the related entity into the other (@Document("Zone") is not needed in your case):

    @Document("Building")
    public class Building {
        private Zone[] zones;
        ...
    }


    public class Zone {
        private String whatever;
        ...
    }
Then in order to get the zones by buildingId, just get the building from the repository and return its nested collection:

zoneRepository.findById(buildingId).getZones()

If as stated in the comments, you have to separate them in different collections I'd suggest you denormalize the information:

    @Document("Building")
    public class Building {
        private Zone[] zones;
        ...
    }


    @Document("Building")
    public class Zone {
        private String buildindId;
        ...
    }
@Repository
public interface ZoneRepository extends  MongoRepository <Zone, Long> {


    @Query(value = "{'buildingId': ?0}")
    List<Zone> queryZoneByBuildingId(@Param("id") Long id);
}
Regarding cascades: MongoDb doesn't support cascading deletes (see: mongoDB alternatives for foreign key constraints). The easiest way to deal with them is using embedded documents (when you delete the parent, its nested collection will be deleted).

In your case, however you would have to delete them manually when you delete the parent entity:

public void deleteBuilding(String buildingId) {

    buildingRepository.deleteById(buildingId);
    //delete zones with buildingId 

}