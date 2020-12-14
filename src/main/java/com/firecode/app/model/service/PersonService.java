package com.firecode.app.model.service;

import com.firecode.app.model.entity.PersonEntity;
import com.firecode.app.model.repository.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonDao dao;

    public PersonEntity create(PersonEntity entity) {
        return dao.create(entity);
    }

    public PersonEntity update(PersonEntity entity) {
        return dao.update(entity);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

}
