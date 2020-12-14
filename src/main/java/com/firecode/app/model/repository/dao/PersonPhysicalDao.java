package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.PersonPhysicalEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.firecode.app.model.repository.PersonPhysicalRepository;
import org.springframework.stereotype.Component;

@Component
public class PersonPhysicalDao implements GenericDao<PersonPhysicalEntity> {

    @Autowired
    private PersonPhysicalRepository repository;

    @Override
    public PersonPhysicalEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<PersonPhysicalEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public PersonPhysicalEntity create(PersonPhysicalEntity t) {
        return repository.save(t);
    }

    @Override
    public PersonPhysicalEntity update(PersonPhysicalEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public PersonPhysicalEntity findByDocument(String value) {
        return repository.findByDocument(value);
    }

}
