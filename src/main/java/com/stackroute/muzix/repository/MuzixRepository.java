package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Muzix;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MuzixRepository extends MongoRepository<Muzix,Integer> {

    @Query(value="db.Muzix.find({trackName:?}).forEach(printjson);" )//,nativeQuery=true)
    public List<Muzix> searchByName(String trackName);

}
