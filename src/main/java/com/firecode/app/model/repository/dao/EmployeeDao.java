package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.EmployeeEntity;
import com.firecode.app.model.repository.EmployeeRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao implements GenericDao<EmployeeEntity> {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public EmployeeEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public EmployeeEntity create(EmployeeEntity t) {
        return repository.save(t);
    }

    @Override
    public EmployeeEntity update(EmployeeEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Page<EmployeeEntity> findAllPaginator(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
