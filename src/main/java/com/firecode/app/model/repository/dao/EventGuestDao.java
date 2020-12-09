package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.EventGuestEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import com.firecode.app.model.repository.EventGuestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class EventGuestDao implements GenericDao<EventGuestEntity> {

    @Autowired
    private EventGuestRepository repository;

    @Override
    public EventGuestEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<EventGuestEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public EventGuestEntity create(EventGuestEntity t) {
        return repository.save(t);
    }

    @Override
    public EventGuestEntity update(EventGuestEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public void delete(EventGuestEntity t) {
        repository.delete(t);
    }

    public Page<EventGuestEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
