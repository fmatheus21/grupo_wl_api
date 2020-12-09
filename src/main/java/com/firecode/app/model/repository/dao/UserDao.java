package com.firecode.app.model.repository.dao;

import com.firecode.app.model.entity.UserEntity;
import com.firecode.app.model.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class UserDao implements GenericDao<UserEntity> {

    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity findById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> findAll(String orderBy) {
        return repository.findAll(Sort.by(Sort.Order.asc(orderBy)));
    }

    @Override
    public UserEntity create(UserEntity t) {
        return repository.save(t);
    }

    @Override
    public UserEntity update(UserEntity t) {
        return repository.save(t);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Page<UserEntity> findAllPaginator(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<UserEntity> findByUsername(String value) {
        return repository.findByUsername(value);
    }

}
