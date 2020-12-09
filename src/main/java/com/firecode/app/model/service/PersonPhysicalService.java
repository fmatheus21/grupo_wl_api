package com.firecode.app.model.service;

import com.firecode.app.model.entity.PersonPhysicalEntity;
import com.firecode.app.model.repository.dao.PersonPhysicalDao;
import org.springframework.stereotype.Service;

@Service
public class PersonPhysicalService extends PersonPhysicalDao {

    @Override
    public PersonPhysicalEntity create(PersonPhysicalEntity entity) {
        return super.create(entity);
    }

    @Override
    public PersonPhysicalEntity update(PersonPhysicalEntity entity) {
        return super.update(entity);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id);
    }

    @Override
    public PersonPhysicalEntity findByDocument(String value) {
        return super.findByDocument(value);
    }

}
