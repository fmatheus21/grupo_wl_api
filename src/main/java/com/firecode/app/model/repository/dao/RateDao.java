package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.RateEntity;
import com.firecode.app.model.repository.RateRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class RateDao implements GenericDao<RateEntity> {

    @Autowired
    private RateRepository repository;

    @Override
    public RateEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<RateEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public RateEntity create(RateEntity t) {
        return repository.save(t);
    }

    @Override
    public RateEntity update(RateEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Page<RateEntity> findAllPaginator(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
