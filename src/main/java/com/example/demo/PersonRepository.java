package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@CrossOrigin(origins = "*")
public interface PersonRepository extends MongoRepository<Person, String> {
    List<Person> findByLastName(@Param("name") String name);
}
