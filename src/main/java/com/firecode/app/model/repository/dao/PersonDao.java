package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.PersonEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.firecode.app.model.repository.PersonRepository;
import org.springframework.stereotype.Component;

@Component
public class PersonDao implements GenericDao<PersonEntity> {

    @Autowired
    private PersonRepository repository;

    @Override
    public PersonEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<PersonEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public PersonEntity create(PersonEntity t) {
        return repository.save(t);
    }

    @Override
    public PersonEntity update(PersonEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

}
