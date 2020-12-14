package com.firecode.app.model.service;

import com.firecode.app.model.entity.EventGuestEntity;
import com.firecode.app.model.repository.dao.EventGuestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventGuestService {

    @Autowired
    private EventGuestDao dao;

    public EventGuestEntity create(EventGuestEntity t) {
        return dao.create(t);
    }

    public void delete(EventGuestEntity t) {
        dao.delete(t);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public EventGuestEntity findById(int id) {
        return dao.findById(id);
    }

    public Page<EventGuestEntity> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    public EventGuestEntity findByDocument(String value) {
        return dao.findByDocument(value);
    }

}
