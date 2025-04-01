package br.com.acbueno.holambra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.com.acbueno.holambra.model.CanMessage;

@Repository
public interface CanRepository extends MongoRepository<CanMessage, String> {

}
