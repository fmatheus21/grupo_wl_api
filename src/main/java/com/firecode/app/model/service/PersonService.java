package com.firecode.app.model.service;

import com.firecode.app.model.entity.PersonEntity;
import com.firecode.app.model.repository.dao.PersonDao;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends PersonDao {

    @Override
    public PersonEntity create(PersonEntity entity) {
        return super.create(entity);
    }

    @Override
    public PersonEntity update(PersonEntity entity) {
        return super.update(entity);
    }

    @Override
    public void deleteById(int id) {
        super.deleteById(id);
    }

}
