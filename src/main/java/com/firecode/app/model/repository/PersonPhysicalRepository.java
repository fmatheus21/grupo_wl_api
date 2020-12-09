package com.firecode.app.model.repository;

import com.firecode.app.model.entity.PersonPhysicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonPhysicalRepository extends JpaRepository<PersonPhysicalEntity, Integer> {

    public PersonPhysicalEntity findByDocument(String value);

}
