package com.firecode.app.model.service;

import com.firecode.app.model.entity.PersonPhysicalEntity;
import com.firecode.app.model.repository.dao.PersonPhysicalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonPhysicalService {

    @Autowired
    private PersonPhysicalDao dao;

    public PersonPhysicalEntity create(PersonPhysicalEntity entity) {
        return dao.create(entity);
    }

    public PersonPhysicalEntity update(PersonPhysicalEntity entity) {
        return dao.update(entity);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public PersonPhysicalEntity findByDocument(String value) {
        return dao.findByDocument(value);
    }

}
